package org.iesch.a02_registro_superheroes

import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.health.connect.datatypes.units.Power
import android.os.Bundle
import android.os.Environment
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.FileProvider
import androidx.core.graphics.drawable.toBitmap
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a02_registro_superheroes.databinding.ActivityRegisterBinding
import org.iesch.a02_registro_superheroes.detalle.DetalleHeroeActivity
import org.iesch.a02_registro_superheroes.model.SuperHeroe
import java.io.File

class RegisterActivity : AppCompatActivity() {

    private lateinit var binding: ActivityRegisterBinding
    private lateinit var heroImage: ImageView
    // 9 - Creamos una variable que va a manejar el resultado de haber hecho la foto
    private var heroBitmap: Bitmap? = null
    //cambiamos el take pictureprewie por take picture
    private var picturepath = ""
    private val getContent = registerForActivityResult(ActivityResultContracts.TakePicture()){
        // Esto nos va a devolver un objeto de tipo bitmap
       // ahora en lugar de un bitmap nos va devolver un booleano si la toma
        //de la foto es exitosa
        success ->
            if (success && picturepath.isNotEmpty()){
                // cualquier imagen del directoru¡io la pòdemeos convertir
                    //en un bitmap
                heroBitmap = BitmapFactory.decodeFile(picturepath)
                //pintamos la imagen en el cualquier cuadradito
                heroImage.setImageBitmap(heroBitmap)
            }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnGuardar.setOnClickListener {

            val superHeroName = binding.etHeroName.text.toString()
            val alterEgo = binding.etAlterEgo.text.toString()
            val bio = binding.etBio.text.toString()
            val power = binding.rbPower.rating

            val superHeroe = SuperHeroe(superHeroName, alterEgo, bio, power)
            irADetalleHeroe( superHeroe )

        }

            // 10
        heroImage = binding.superheroImage
        binding.superheroImage.setOnClickListener {
            openCamera()
        }
    }

    private fun openCamera() {
        // 2 aqui debemos crear un path temporal para gurdar la imagen

        val imageFile = createImageFile()

        //ahora ya tenemos el file pero nesecitamos el uri
        //como estampos por encima de la sdk 24 obtendre mos la uri atravez de file provider file provider lo que hace es compartir file con otros de forma segura
        val uri = FileProvider.getUriForFile(this,"${applicationContext.packageName}.provider",imageFile)
        getContent.launch(uri)

    }

    //3-esta funcion crea un file y de ese file recuperaremos la uri
    private fun createImageFile() : File {
        val fileName = "superhero_image"
        // este es el directorio donde almacenamos la imagen por defecto es directory pictures
        val fileDirectory = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        // creamos nuestro file, y aqui nos pide el nombre,el formato,el directorio
        val imageFile = File.createTempFile(fileName,".jpg",fileDirectory)
        //Ahoa ya podemos guradar el path en la variable global
        picturepath = imageFile.absolutePath
        return imageFile
    }

    private fun irADetalleHeroe( superHeroe: SuperHeroe ) {

        val intent = Intent(this, DetalleHeroeActivity::class.java)
        intent.putExtra(DetalleHeroeActivity.SUPERHEROE_KEY, superHeroe)
        // 12 - Añado el objeto bitmap al intent
        intent.putExtra(DetalleHeroeActivity.FOTO_KEY, heroImage.drawable.toBitmap())
        startActivity(intent)
    }
}