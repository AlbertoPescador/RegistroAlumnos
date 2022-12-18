package com.example.registroalumnos.BaseDatos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface DAO {


    //  Insert
    @Insert
    suspend fun anadirAlumno(elemento: TablaAlumnos): Long

    //  Update
    @Query("UPDATE alumnos SET curso = :curso WHERE nombre like :nombre")
    suspend fun updateAlumno(nombre: String, curso: String) : Int

    //  Delete
    @Query("DELETE FROM alumnos where nombre like :nombre")
    suspend fun deleteAlumno(nombre: String) : Int
}