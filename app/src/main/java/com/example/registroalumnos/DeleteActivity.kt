package com.example.registroalumnos

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.TablaAlumnos
import com.example.registroalumnos.BaseDatos.ListaAlumnosAPP.Companion.database
import com.example.registroalumnos.databinding.ActivityDeleteBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DeleteActivity : ActivityWithMenu() {

    private lateinit var deleteBinding : ActivityDeleteBinding
    private lateinit var listAlumnos : MutableList<TablaAlumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        deleteBinding = ActivityDeleteBinding.inflate(layoutInflater)
        setContentView(deleteBinding.root)

        //  Inicializamos la lista
        listAlumnos = ArrayList()

        //  Generamos metodo para el click del boton
        deleteBinding.btneliminaralumno.setOnClickListener{
            var alumnoNombre = deleteBinding.nombreeliminar.text.toString()

            //  Validacion
            if (alumnoNombre.isEmpty()){
                Toast.makeText(this, "El campo no puede estar vacío", Toast.LENGTH_SHORT).show()
            } else{
                var alumno = TablaAlumnos(nombre = alumnoNombre)

                //  Eliminamos alumno de la BD
                eliminarAlumno(alumno)
                //  Mensaje de alumno eliminado
                Toast.makeText(this, "El alumno ha sido eliminado", Toast.LENGTH_SHORT).show()

                //  Limpiamos los campos
                limpiarCampos()

                //  Cerramos el teclado
                cerrarTeclado()

            }
        }
    }

    //  Funcion para borrar alumno
    fun eliminarAlumno(alumnoNombre: TablaAlumnos){
        CoroutineScope(Dispatchers.IO).launch {
            database.DAO().deleteAlumno(alumnoNombre.nombre)
        }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(deleteBinding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        deleteBinding.nombreeliminar.text.clear()
    }


}