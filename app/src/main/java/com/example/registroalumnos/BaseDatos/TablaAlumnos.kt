package com.example.registroalumnos.BaseDatos

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "alumnos")
class TablaAlumnos (
        @PrimaryKey(autoGenerate = true)
        var id: Int = 0,
        var nombre: String = "",
        var apellido: String = "",
        var curso: String = ""
)