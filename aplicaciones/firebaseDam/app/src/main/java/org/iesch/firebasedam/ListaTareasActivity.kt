package org.iesch.firebasedam

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.firestore.FirebaseFirestore
import org.iesch.a08_firebasedam.recycler.TareaAdapter

import org.iesch.firebasedam.databinding.ActivityListaTareasBinding
import org.iesch.firebasedam.model.Tarea

class ListaTareasActivity : AppCompatActivity() {

    private lateinit var binding: ActivityListaTareasBinding
    // Creamos la instancia para la base de datos
    private val db = FirebaseFirestore.getInstance()
    // 6 - Creamos unalista mutable para almacenar las tareas
    private val listaTareas = mutableListOf<Tarea>()
    // 7 - Creamos el adaptador
    private lateinit var tareaAdapter: TareaAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityListaTareasBinding.inflate( layoutInflater )
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // 8 Configuramos nuestro RecyclerView
        configurarRecyclerView()
        // 10 Cargamos las tareas desde Firebase Firestore
        cargarTareas()
        // 12 Configuramoslos botones
        initUI()
    }

    private fun initUI() {
        binding.btnAgregar.setOnClickListener {
            agregarTarea()
        }
    }

    private fun agregarTarea() {
        val titulo = binding.etTitulo.text.toString().trim()
        val descripcion = binding.etDescripcion.text.toString().trim()
        if ( titulo.isEmpty() || descripcion.isEmpty() )  {
            Toast.makeText(this, "Debes rellenar los campos!!", Toast.LENGTH_LONG).show()
        } else {
            // creamos una nueva tarea
            val nuevaTareaRef = db.collection("tareas").document()
            val nuevaTarea = Tarea(
                id = nuevaTareaRef.id,
                titulo = titulo,
                descripcion = descripcion,
                completada = false
            )
            nuevaTareaRef.set(nuevaTarea)
                .addOnCompleteListener {
                    Toast.makeText(this, "Tarea añadida correctamente", Toast.LENGTH_LONG).show()
                    // limpiamos los campos de titulo y descripcion
                    binding.etTitulo.text.clear()
                    binding.etDescripcion.text.clear()
                    // Recargamos la lista de tareas
                    cargarTareas()
                }
                .addOnFailureListener {
                    Toast.makeText(this, "Error al añadir la tarea", Toast.LENGTH_LONG).show()
                }

        }
    }

    private fun cargarTareas() {
        db.collection("tareas")
            .addSnapshotListener { snapshot, error ->
                if ( error != null ) {
                    Toast.makeText(this, "Error al cargar las tareas", Toast.LENGTH_LONG).show()
                    return@addSnapshotListener
                }
                listaTareas.clear()
                snapshot?.documents?.forEach { document ->
                    val tarea = Tarea(
                        id = document.id,
                        titulo = document.getString("titulo") ?: "",
                        descripcion = document.getString("descripcion") ?: "",
                        completada = document.getBoolean("completada") ?: false
                    )
                    listaTareas.add(tarea)
                }

                tareaAdapter.actualizarLista( listaTareas )

            }

    }

    private fun configurarRecyclerView() {
        tareaAdapter = TareaAdapter( listaTareas )
        // 9 - Asignamos el adaptador a nuestro RecyclerView
        binding.rvTareas.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        binding.rvTareas.adapter = tareaAdapter
    }
}