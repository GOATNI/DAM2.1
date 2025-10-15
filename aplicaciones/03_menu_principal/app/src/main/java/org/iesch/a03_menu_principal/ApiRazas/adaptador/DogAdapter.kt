package org.iesch.a03_menu_principal.ApiRazas.adaptador


import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.iesch.a03_menu_principal.R

//nuestro adaptador recibira una lista de Strings letenemos que pasar ViewHolder

class DogAdapter(val imagenes: List<String>) : RecyclerView.Adapter<DogViewHolder>(){
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): DogViewHolder {
        // aqui tendre mos imflat layout item de cada respuesta
        val layoutInflater = LayoutInflater.from(parent.context)
        return DogViewHolder(layoutInflater.inflate(R.layout.item_dog,parent,false))
    }

    override fun onBindViewHolder(
        holder: DogViewHolder,
        position: Int
    ) {
        //crearemos el item que sera la imagen y la posicion que tenga y luego llamamos a holder y le da mos el item
        val item = imagenes[position]
        holder.render(item)
    }

    override fun getItemCount(): Int {
        //devuelve el tamño de la LISTA QUE TENGAMOS
        return imagenes.size
    }
}