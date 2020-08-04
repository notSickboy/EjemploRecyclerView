package com.example.ejemplorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity(){

    var lista:RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platillos = ArrayList<Platillo>()

        platillos.add(Platillo("Mole",250.0,4.5F, R.drawable.platillo_01))
        platillos.add(Platillo("Empanadas",250.0,4.5F, R.drawable.platillo_02))
        platillos.add(Platillo("Quepeque",250.0,4.5F, R.drawable.platillo_03))
        platillos.add(Platillo("Romuardo",250.0,4.5F, R.drawable.platillo_04))
        platillos.add(Platillo("Pelaldo",250.0,4.5F, R.drawable.platillo_05))
        platillos.add(Platillo("Petate",250.0,4.5F, R.drawable.platillo_06))
        platillos.add(Platillo("America",250.0,4.5F, R.drawable.platillo_07))
        platillos.add(Platillo("Pollo",250.0,4.5F, R.drawable.platillo_08))
        platillos.add(Platillo("Alitas",250.0,4.5F, R.drawable.platillo_09))
        platillos.add(Platillo("Mole dulce",250.0,4.5F, R.drawable.platillo_10))


        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(platillos, object:ClickListener{
            override fun onClick(vista: View, index: Int) {
                Toast.makeText(applicationContext,platillos.get(index).nombre,Toast.LENGTH_SHORT).show()
            }

        })
        lista?.adapter = adaptador

    }

}