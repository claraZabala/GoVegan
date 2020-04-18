package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.calendari_setmanal.*


class Calendari_Setmanal : AppCompatActivity() {
    var controlador: Controlador
    init {
        controlador = Controlador
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendari_setmanal)

        //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
        //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
        val spinner: Spinner = findViewById(R.id.spinner2)
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
            spinner.adapter = adapter
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

        if (controlador.getSetRecepta()){
            int1.setOnClickListener(clickListener)
            int2.setOnClickListener(clickListener)
            int3.setOnClickListener(clickListener)
            int4.setOnClickListener(clickListener)
            int5.setOnClickListener(clickListener)
            int6.setOnClickListener(clickListener)
            int7.setOnClickListener(clickListener)
            int8.setOnClickListener(clickListener)
            int9.setOnClickListener(clickListener)
            int10.setOnClickListener(clickListener)
            int11.setOnClickListener(clickListener)
            int12.setOnClickListener(clickListener)
            int13.setOnClickListener(clickListener)
            int14.setOnClickListener(clickListener)
            int15.setOnClickListener(clickListener)
            int16.setOnClickListener(clickListener)
            int17.setOnClickListener(clickListener)
            int18.setOnClickListener(clickListener)
            int19.setOnClickListener(clickListener)
            int20.setOnClickListener(clickListener)
            int21.setOnClickListener(clickListener)
        }
    }

    private val clickListener = View.OnClickListener { view ->
        when (view.id) {
            R.id.int1 -> controlador.setDiaRecepta("dilluns", "esmorzar",spinner2.selectedItem.toString())
            R.id.int2 -> controlador.setDiaRecepta("dimarts", "esmorzar",spinner2.selectedItem.toString())
            R.id.int3 -> controlador.setDiaRecepta("dimecres", "esmorzar",spinner2.selectedItem.toString())
            R.id.int4 -> controlador.setDiaRecepta("dijous","esmorzar",spinner2.selectedItem.toString())
            R.id.int5 -> controlador.setDiaRecepta("divendres","esmorzar",spinner2.selectedItem.toString())
            R.id.int6 -> controlador.setDiaRecepta("dissabte","esmorzar",spinner2.selectedItem.toString())
            R.id.int7 -> controlador.setDiaRecepta("diumenge","esmorzar",spinner2.selectedItem.toString())
            R.id.int8 -> controlador.setDiaRecepta("dilluns","dinar",spinner2.selectedItem.toString())
            R.id.int9 -> controlador.setDiaRecepta("dimarts","dinar",spinner2.selectedItem.toString())
            R.id.int10 -> controlador.setDiaRecepta("dimecres","dinar",spinner2.selectedItem.toString())
            R.id.int11 -> controlador.setDiaRecepta("dijous","dinar",spinner2.selectedItem.toString())
            R.id.int12 -> controlador.setDiaRecepta("divendres","dinar",spinner2.selectedItem.toString())
            R.id.int13 -> controlador.setDiaRecepta("dissabte","dinar",spinner2.selectedItem.toString())
            R.id.int14 -> controlador.setDiaRecepta("diumenge","dinar",spinner2.selectedItem.toString())
            R.id.int15 -> controlador.setDiaRecepta("dilluns","sopar",spinner2.selectedItem.toString())
            R.id.int16 -> controlador.setDiaRecepta("dimarts","sopar",spinner2.selectedItem.toString())
            R.id.int17 -> controlador.setDiaRecepta("dimecres","sopar",spinner2.selectedItem.toString())
            R.id.int18 -> controlador.setDiaRecepta("dijous","sopar",spinner2.selectedItem.toString())
            R.id.int19 -> controlador.setDiaRecepta("divendres","sopar",spinner2.selectedItem.toString())
            R.id.int20 -> controlador.setDiaRecepta("dissabte","sopar",spinner2.selectedItem.toString())
            R.id.int21 -> controlador.setDiaRecepta("diumenge","sopar",spinner2.selectedItem.toString())
        }
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
