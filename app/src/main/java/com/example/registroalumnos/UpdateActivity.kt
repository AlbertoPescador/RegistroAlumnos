package com.example.registroalumnos

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.ListaAlumnosAPP
import com.example.registroalumnos.BaseDatos.TablaAlumnos
import com.example.registroalumnos.databinding.ActivityUpdateBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class UpdateActivity : ActivityWithMenu() {

    private lateinit var updateBinding : ActivityUpdateBinding
    private lateinit var listAlumnos : MutableList<TablaAlumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        updateBinding = ActivityUpdateBinding.inflate(layoutInflater)
        setContentView(updateBinding.root)

        //  Inicializamos la lista
        listAlumnos = ArrayList()

        //  Generamos metodo para el click del boton
        updateBinding.btnactualizarcurso.setOnClickListener{

            var alumnoNombre = updateBinding.nombrealumno.text.toString()
            var curso = updateBinding.nuevocurso.text.toString()

            //  Validaciones
            if (alumnoNombre.isEmpty() || curso.isEmpty()){
                Toast.makeText(this, "Los campos nos pueden estar vacíos", Toast.LENGTH_SHORT).show()
            }
            else{
                var alumno = TablaAlumnos(nombre = alumnoNombre, curso = curso)

                //  Actualizamos alumno en la lista
                actualizarAlumno(alumno)
                //  Mensaje de que alumno se ha actualizado
                Toast.makeText(this, "El alumno se ha actualizado", Toast.LENGTH_SHORT).show()

                //  Limpiamos los campos
                limpiarCampos()

                //  Cerramos el teclado
                cerrarTeclado()
            }
        }
    }

    //  Funcion para actualizar alumno
    fun actualizarAlumno(alumnoNombre: TablaAlumnos){
        CoroutineScope(Dispatchers.IO).launch {
            ListaAlumnosAPP.database.DAO().updateAlumno(alumnoNombre.nombre, alumnoNombre.curso)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(updateBinding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        updateBinding.nombrealumno.text.clear()
        updateBinding.nuevocurso.text.clear()
    }

}