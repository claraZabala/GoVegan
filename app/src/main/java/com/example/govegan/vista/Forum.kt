package com.example.govegan.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Pregunta
import com.example.govegan.model.Resposta


class Forum : AppCompatActivity() {
    var controlador:Controlador = Controlador();
    val pregReceptes = arrayOf<Pregunta>()
    val pregOnCompro = arrayOf<Pregunta>()
    val pregDietes = arrayOf<Pregunta>()
    val pregRestaurants = arrayOf<Pregunta>()
    val pregPropietats = arrayOf<Pregunta>()
    val pregModa = arrayOf<Pregunta>()
    val pregHigiene = arrayOf<Pregunta>()
    val pregSalut = arrayOf<Pregunta>()
    val pregAltres = arrayOf<Pregunta>()

    fun initPreguntes(){
        //Fem que per cada pregunta cridi al metode de la classe pregunta init respostes
        // Falta completar
    }

    fun crearPregunta(){
        //Crea una pregunta i l'afegeix a l'arrai i fem que crei un layout amb el botó
        //Falta completar
    }

    fun mostrarPreguntesPerTema(){
        // Fem un when per cada tema i mostrem l'array pel layout o que el retorni
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.forum)

        //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
        //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
        val spinner: Spinner = findViewById(R.id.spinner1)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.preguntes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.setAdapter(adapter)
            spinner.onItemSelectedListener = object : OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        adapterView.context,
                        adapterView.getItemAtPosition(pos) as String, Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        }
    }

}
