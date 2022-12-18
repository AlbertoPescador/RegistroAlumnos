package com.example.registroalumnos

import android.app.Activity
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.example.registroalumnos.BaseDatos.TablaAlumnos
import com.example.registroalumnos.BaseDatos.ListaAlumnosAPP.Companion.database
import com.example.registroalumnos.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MainActivity : ActivityWithMenu() {

    private lateinit var binding : ActivityMainBinding
    private lateinit var listAlumnos : MutableList<TablaAlumnos>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        //  Inicializamos la lista
        listAlumnos = ArrayList()

        //  Generamos metodo para el click del boton
        binding.btnanadir.setOnClickListener {
            var nombreAlumno = binding.nombre.text.toString()
            var apellidoAlumno = binding.apellido.text.toString()
            var curso = binding.curso.text.toString()

            //  Validación de los cammpos
            if (nombreAlumno.isEmpty() || apellidoAlumno.isEmpty() || curso.isEmpty()){
                Toast.makeText(this, "Los campos nos puedes ser nulos", Toast.LENGTH_SHORT).show()
            }else{
                //  Creamos la variable que inserta el alumno
                var alumnoInsertado = TablaAlumnos(nombre = nombreAlumno, apellido = apellidoAlumno, curso = curso)

                //  Añadimos alumno en la lista
                listAlumnos.add(alumnoInsertado)

                //  Añadimos alumno a la base de datos
                insertarAlumno(alumnoInsertado)
                //  Mensaje de alumno añadido
                Toast.makeText(this, "Alumno añadido a la base de datos", Toast.LENGTH_SHORT).show()

                //  Limpiamos los campos
                limpiarCampos()

                //  Cerramos el teclado
                cerrarTeclado()
            }
        }
    }

    //  Funcion para añadir alumno
    fun insertarAlumno(alumno: TablaAlumnos){
       CoroutineScope(Dispatchers.IO).launch{
           database.DAO().anadirAlumno(alumno)
       }
    }

    // Funcion para cerrar el teclado
    fun cerrarTeclado() {
        val imm = getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(binding.root.windowToken, 0)
    }

    // Funcion para limpiar los campos
    fun limpiarCampos() {
        binding.nombre.text.clear()
        binding.apellido.text.clear()
        binding.curso.text.clear()
    }
}