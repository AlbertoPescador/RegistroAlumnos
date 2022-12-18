package com.example.registroalumnos.BaseDatos

import android.app.Application
import androidx.room.Room


class ListaAlumnosAPP : Application() {
    companion object {
        lateinit var database: BDAlumnos
    }

    override fun onCreate(){
        super.onCreate()
        database = Room.databaseBuilder(
            this,
            BDAlumnos::class.java,
            "DBAlumnos"
        ).build()
    }
}