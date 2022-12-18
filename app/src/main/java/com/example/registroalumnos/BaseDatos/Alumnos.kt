package com.example.registroalumnos.BaseDatos

import androidx.room.Database
import androidx.room.RoomDatabase

    @Database(
        entities = [TablaAlumnos::class],
        version = 1
    )

    abstract class BDAlumnos: RoomDatabase(){
        abstract fun DAO(): DAO
    }
