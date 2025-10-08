package org.iesch.a4_recyclerbasicoandroid.adapter

import android.R
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


/*
Creamos el adaptador
el adaptador es el punte entre nuestro datos y recycles
-se encarga de crear las vias para cada elemento
- Enlazar los datos con las vistas
- Indicar cuantas o cuntos elementos hay
*/
class VersionesAndroidAdapter(val listaVersiones: List<String>) : RecyclerView.Adapter<AndroidVersionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AndroidVersionViewHolder {
        //paso 5: Crea una nueva vista cuando es nesesario
        // infla  el layout para cada item
        val layyoutInfalater = LayoutInflater.from( parent.context)
        return AndroidVersionViewHolder(layyoutInfalater.inflate(R.layout.simple_list_item_1, parent,false))

    }

    override fun onBindViewHolder(
        holder: AndroidVersionViewHolder,
        position: Int
    ) {
        //paso 6: este es el metodo que pinta los atributos
        val nombre_version = listaVersiones[ position ]
        holder.render( nombre_version )
    }
    // paso 4:Este metodo devuelve el numero total de elementos
    override fun getItemCount(): Int {
        return listaVersiones.size
    }

}

class AndroidVersionViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

    // paso 7: Aqui iria el codigo para pintar los atributos
    //metodo de convinencia para usar los datos

    fun render(version: String){
        itemView.findViewById<TextView>(R.id.text1).text = version
        // Añadir el listener para tomar el control
        itemView.setOnClickListener {
            Toast.makeText(itemView.context,version, Toast.LENGTH_SHORT).show()
        }
    }

}