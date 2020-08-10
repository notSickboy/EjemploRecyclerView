package com.example.ejemplorecyclerview

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RatingBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class AdaptadorCustom(items:ArrayList<Platillo>, var listener:ClickListener, var longClickListener:LongClickListener):RecyclerView.Adapter<AdaptadorCustom.ViewHolder>() {


    var items:ArrayList<Platillo>? = null
    var multiSeleccion = false

    var itemsSeleccionados:ArrayList<Int>? = null
    var viewHolder:ViewHolder? = null

    init {
        this.items = items
        itemsSeleccionados = ArrayList()
    }

    // Funcion que implementa el archivo .xml en la vista
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AdaptadorCustom.ViewHolder{
        val vista = LayoutInflater.from(parent?.context).inflate(R.layout.template_platillo,parent, false)
        viewHolder = ViewHolder(vista, listener, longClickListener)

        return viewHolder!!
    }

    // Funcion que cuenta los elementos de la vista
    override fun getItemCount(): Int {
        return items?.count()!!
    }

    // Mapea los elementos graficos con los datos
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = items?.get(position)
        holder.foto?.setImageResource(item?.foto!!)
        holder.nombre?.text = item?.nombre
        holder.precio?.text = "$" + item?.precio.toString()
        holder.rating?.rating = item?.rating!!

        if(itemsSeleccionados?.contains(position)!!){
            holder.vista.setBackgroundColor(Color.LTGRAY)
        } else {
            holder.vista.setBackgroundColor(Color.WHITE)
        }
    }

    fun iniciarActionMode(){
        multiSeleccion = true
    }

    fun destruirActionMode(){
        multiSeleccion = false
        itemsSeleccionados?.clear()
        notifyDataSetChanged()
    }

    fun terminarActionMode(){
        // Eliminar elementos seleccionados
        for(item in itemsSeleccionados!!){
            itemsSeleccionados?.remove(item)
        }
        multiSeleccion = false
        notifyDataSetChanged()
    }

    fun seleccionarItem(index:Int){
        if(multiSeleccion){
            if(itemsSeleccionados?.contains(index)!!){
                itemsSeleccionados?.remove(index)
            } else {
                itemsSeleccionados?.add(index)
            }

            notifyDataSetChanged()
        }
    }

    fun contar_items_seleccionados_en_longclick():Int{
        return itemsSeleccionados?.count()!!
    }

    fun eliminar_items_seleccionados(){
        if(itemsSeleccionados?.count()!! > 0){
            var items_a_eliminar = ArrayList<Platillo>()

            for(index in itemsSeleccionados!!){
                items_a_eliminar.add(items?.get(index)!!)
            }

            items?.removeAll(items_a_eliminar)
            itemsSeleccionados?.clear()
        }
    }

    class ViewHolder (vista:View, listener:ClickListener, longClickListener: LongClickListener):RecyclerView.ViewHolder(vista), View.OnClickListener,View.OnLongClickListener{
        var vista = vista
        var foto:ImageView? = null
        var nombre:TextView? = null
        var precio:TextView? = null
        var rating:RatingBar? = null
        var listener:ClickListener? = null
        var longListener:LongClickListener? = null

        init {
            foto = vista.findViewById(R.id.ivFoto)
            nombre = vista.findViewById(R.id.tvNombre)
            precio = vista.findViewById(R.id.tvPrecio)
            rating = vista.findViewById(R.id.tvRating)

            this.listener = listener
            this.longListener = longClickListener

            vista.setOnClickListener(this)
            vista.setOnLongClickListener(this)
        }

        override fun onClick(v: View?) {
            this.listener?.onClick(v!!, adapterPosition)
        }

        override fun onLongClick(v: View?): Boolean {
            this.longListener?.longClick(v!!,adapterPosition)
            return true
        }
    }


}