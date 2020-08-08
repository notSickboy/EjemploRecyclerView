package com.example.ejemplorecyclerview

import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode
import androidx.appcompat.view.ActionMode.*
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

class MainActivity : AppCompatActivity(){

    var lista:RecyclerView? = null
    var adaptador:AdaptadorCustom? = null
    var layoutManager:RecyclerView.LayoutManager? = null

    var isActionMode = false

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


        val callback = object : androidx.appcompat.view.ActionMode.Callback{
            override fun onActionItemClicked(mode: androidx.appcompat.view.ActionMode?, item: MenuItem?): Boolean {
                adaptador?.terminarActionMode()
                mode?.finish()
                isActionMode = false
                return true
            }

            override fun onCreateActionMode(mode: androidx.appcompat.view.ActionMode?, menu: Menu?): Boolean {
                // Inicializar action mode
                adaptador?.iniciarActionMode()
                return true
            }

            override fun onPrepareActionMode(mode: androidx.appcompat.view.ActionMode?, menu: Menu?): Boolean {
                return false
            }

            override fun onDestroyActionMode(mode: androidx.appcompat.view.ActionMode?) {
                adaptador?.destruirActionMode()
                isActionMode = false
            }

        }

        adaptador = AdaptadorCustom(platillos, object:ClickListener{
            override fun onClick(vista: View, index: Int) {
                Toast.makeText(applicationContext,platillos.get(index).nombre,Toast.LENGTH_SHORT).show()
            }

        }, object: LongClickListener{
            override fun longClick(vista: View, index: Int) {
                if(!isActionMode){
                    startSupportActionMode(callback)
                    isActionMode = true
                    adaptador?.seleccionarItem(index)
                } else {
                    // selecciono o deselecciono
                    adaptador?.seleccionarItem(index)
                }
            }

        })

        lista?.adapter = adaptador

        // Accion refresh
        val swipeToRefresh = findViewById<SwipeRefreshLayout>(R.id.swipeToRefresh)
        swipeToRefresh.setOnRefreshListener {
            for(i in 1..1000000000){
            }
            swipeToRefresh.isRefreshing = false
            platillos.add(Platillo("Nuggets de poio",250.0,4.5F, R.drawable.platillo_01))
            adaptador?.notifyDataSetChanged()
        }

    }

}