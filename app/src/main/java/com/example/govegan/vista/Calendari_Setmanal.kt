package com.example.govegan.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.govegan.R
import kotlinx.android.synthetic.main.calendari_setmanal.*

class Calendari_Setmanal : AppCompatActivity() {
    //var icones: ArrayList<ImageView> = ArrayList()

    //em dona error al inicialitzar
    /*init{
        icones.add(int1)
        icones.add(int2)
        icones.add(int3)
        icones.add(int4)
        icones.add(int5)
        icones.add(int6)
        icones.add(int7)
        icones.add(int8)
        icones.add(int9)
        icones.add(int10)
        icones.add(int12)
        icones.add(int13)
        icones.add(int14)
        icones.add(int15)
        icones.add(int16)
        icones.add(int17)
        icones.add(int18)
        icones.add(int19)
        icones.add(int20)
        icones.add(int21)
    }*/

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendari_setmanal)


        //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
        //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
        val spinner: Spinner = spinner2
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.setmanes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.setAdapter(adapter)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
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

        /*
        * Mètode que defineix el Listener de doble click per a cada icona (àpat)
        * Amb un for es recorren les 21 icones i se'ls aplica l'escoltador
         */
        for (i in 0..20){
            //afegirPlat(icones[i])
        }
    }

    fun afegirPlat(im: ImageView) {
        im.setOnClickListener (
            DoubleClickListener(
                callback = object : DoubleClickListener.Callback {
                    override fun doubleClicked() {
                        println("Hola jaja")
                    }
            }
        ))
    }

    fun afegirMenu(view: View){
        intent = Intent(this, AfegirMenu::class.java)
        startActivity(intent)
    }

    fun recepta(view: View){
        intent = Intent(this, Recepta::class.java)
        startActivity(intent)
    }
}


