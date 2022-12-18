package com.example.registroalumnos

import android.content.Intent
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity

open class ActivityWithMenu: AppCompatActivity() {

    // Indice de la actividad
    companion object{
        var actividadActual = 0;
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menu, menu)
        for (i in 0 until menu!!.size()) {
            if (i == actividadActual) menu.getItem(i).isEnabled = false
            else menu.getItem(i).isEnabled = true
        }
        return true
    }

    // Función que se ejecuta al pulsar un elemento del menú
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.anadir_alumno ->{
                val intent = Intent(this, MainActivity::class.java) //nombre de la actividad
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 0;
                startActivity(intent)
                true
            }
            R.id.eliminar_alumno ->{
                val intent = Intent(this, DeleteActivity::class.java) //nombre de la actividad
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 2;
                startActivity(intent)
                true
            }
            R.id.actualizar_alumno ->{
                val intent = Intent(this, UpdateActivity::class.java) //nombre de la actividad
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                actividadActual = 1;
                startActivity(intent)
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }


}