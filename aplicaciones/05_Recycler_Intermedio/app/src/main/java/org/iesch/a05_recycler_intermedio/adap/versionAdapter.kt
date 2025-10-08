package org.iesch.a05_recycler_intermedio.adap

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

class versionAdapter (val listaVersiones: List<String>) : RecyclerView.Adapter<AndroidVersionViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): AndroidVersionViewHolder {
        TODO("Not yet implemented")
    }

    override fun onBindViewHolder(
        holder: AndroidVersionViewHolder,
        position: Int
    ) {
        TODO("Not yet implemented")
    }

    override fun getItemCount(): Int {
       return listaVersiones.size
    }
}

class AndroidVersionViewHolder(view: View) : RecyclerView.ViewHolder(view)  {

}