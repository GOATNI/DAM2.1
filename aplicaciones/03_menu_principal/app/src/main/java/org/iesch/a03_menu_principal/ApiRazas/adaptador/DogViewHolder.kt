package org.iesch.a03_menu_principal.ApiRazas.adaptador

import android.media.Image
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import org.iesch.a03_menu_principal.databinding.ItemDogBinding

//calse recibira la vista que vamos a pintar
class DogViewHolder(view: View): RecyclerView.ViewHolder(view) {
    //metodo que recibira una imagen con cada celda que tenemos que pintar
    private val binding = ItemDogBinding.bind(view)
    fun render ( image: String){
        //Atravez de la libreria picaso mostraremos la imagen a partir de la url
        Picasso.get().load(image).into(binding.ivDog)

    }
}