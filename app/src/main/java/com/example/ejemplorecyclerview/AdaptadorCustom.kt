package com.example.ejemplorecyclerview

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(var contexto:Context, items:ArrayList<Platillo>):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items:ArrayList<Platillo>? = null

    init {
        this.items = items
    }

    // Funcion que implementa el archivo .xml en la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder{
        val vista = LayoutInflater.from(contexto).inflate(R.layout.template_platillo,parent, false)
        val viewHolder = ViewHolder(vista)

        return viewHolder
    }

    // Funcion que cuenta los elementos de la vista
    override fun getItemCount(): Int {
        return items?.count()!!
    }

    // dfsddfsdf
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = item?.precio.toString()
        holder.rating?.rating = item?.rating!!
    }

    class ViewHolder (vista:View):RecyclerView.ViewHolder(vista){
        var vista = vista
        var foto:ImageView? = null
        var nombre:TextView? = null
        var precio:TextView? = null
        var rating:RatingBar? = null

        init {
            foto = vista.findViewById(R.id.ivFoto)
            nombre = vista.findViewById(R.id.tvNombre)
            precio = vista.findViewById(R.id.tvPrecio)
            rating = vista.findViewById(R.id.tvRating)
        }
    }


}