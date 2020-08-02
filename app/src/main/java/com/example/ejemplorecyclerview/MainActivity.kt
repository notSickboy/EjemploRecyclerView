package com.example.ejemplorecyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity(){

    var lista:RecyclerView? = null
    var adaptador = null
    var layoutManager:RecyclerView.LayoutManager? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val platillos = ArrayList<Platillo>()
        platillos.add(Platillo("Mole",250.0,4.5, R.drawable.platillo01))
        platillos.add(Platillo("Empanadas",250.0,4.5, R.drawable.platillo02))
        platillos.add(Platillo("Quepeque",250.0,4.5, R.drawable.platillo03))
        platillos.add(Platillo("Romuardo",250.0,4.5, R.drawable.platillo04))
        platillos.add(Platillo("Pelaldo",250.0,4.5, R.drawable.platillo05))
        platillos.add(Platillo("Petate",250.0,4.5, R.drawable.platillo06))
        platillos.add(Platillo("America",250.0,4.5, R.drawable.platillo07))
        platillos.add(Platillo("Pollo",250.0,4.5, R.drawable.platillo08))
        platillos.add(Platillo("Alitas",250.0,4.5, R.drawable.platillo09))
        platillos.add(Platillo("Mole dulce",250.0,4.5, R.drawable.platillo10))


        lista = findViewById(R.id.lista)
        lista?.setHasFixedSize(true)

        layoutManager = LinearLayoutManager(this)
        lista?.layoutManager = layoutManager

        adaptador = AdaptadorCustom(platillos)
        lista?.adapter = adaptador

    }

}