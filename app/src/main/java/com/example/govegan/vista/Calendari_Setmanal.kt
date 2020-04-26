package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.calendari_setmanal.*
import kotlinx.android.synthetic.main.dialog_afegir_plat.view.*

class Calendari_Setmanal : AppCompatActivity() {
    var controlador: Controlador
    init {
        controlador = Controlador
    }

    //quan s'inicialitza l'app es carrega la setmana actual, que ha de canviar cada setmana
    var setmanaActual: String
    init {
        //TODO: gestionar la inicialització de la setmana
        setmanaActual = "Setmana 1"
    }

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

        //si vens d'una recepta és true i es creen els on click listener
        //un click per triar el dia i àpat en què s'afegeix la recepta
        if (controlador.getSetRecepta()){
            toast("Selecciona un dia i àpat per afegir la recepta que has escollit")
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

        /*
        * Si es fa dobleclick en ua icona, s'obre un dialog per a afegir àpat
        * afegirPlat estableix l'escoltador del DobleClick a totes les icones
         */
        afegirPlat(int1)
        afegirPlat(int2)
        afegirPlat(int3)
        afegirPlat(int4)
        afegirPlat(int5)
        afegirPlat(int6)
        afegirPlat(int7)
        afegirPlat(int8)
        afegirPlat(int9)
        afegirPlat(int10)
        afegirPlat(int11)
        afegirPlat(int12)
        afegirPlat(int13)
        afegirPlat(int14)
        afegirPlat(int15)
        afegirPlat(int16)
        afegirPlat(int17)
        afegirPlat(int18)
        afegirPlat(int19)
        afegirPlat(int20)
        afegirPlat(int21)
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

    /*
    * Estableox l'escoltador per quan es fa doble click en una icona.
    * S'obre un AlertDialog on omplir el nom i si és vegà l'àpat i s'afegeix la informació a les
    * dades de l'usuari, a banda de canviar la icona del calendari per la adient.
     */
    fun afegirPlat(im: ImageView) {
        im.setOnClickListener (
            DoubleClickListener(
                callback = object : DoubleClickListener.Callback {
                    override fun doubleClicked() {
                        //s'obre l'AlertDialog per a omplir les dades
                        val dialog = AlertDialog.Builder(this@Calendari_Setmanal)
                        val dialogView = layoutInflater.inflate(R.layout.dialog_afegir_plat, null)
                        dialog.setView(dialogView)
                        dialog.setCancelable(false)
                        val mAlertDialog = dialog.show()
                        val customDialog = dialog.create()

                        //quan es prem el botò es desen les informacions
                        dialogView.afegirPlatCalendari.setOnClickListener{
                            val titol = dialogView.resposta.text.toString()
                            val carnivor = dialogView.teCarn.isChecked
                            val vegetaria = dialogView.teDerivats.isChecked
                            var categoria: Int = 0
                            if (carnivor) categoria = 2
                            else if (vegetaria) categoria = 1

                            //s'afegeix la info a memòria
                            //es reutilitza la funció setReceptaFromProposta per a tenir a
                            // Controlador el titol de la recepta, tot i que o provingui d'un aproposta
                            controlador.setTitolReceptaFromCalendari(titol)
                            escollirDiaIApat(im.id)

                            mAlertDialog.dismiss()
                        }
                    }
            }
        ))
    }



    fun afegirMenu(view: View){
        intent = Intent(this, AfegirProposta::class.java)
        startActivity(intent)
    }

    fun recepta1(view: View){
        if (controlador.getReceptaByName(esmorzar.text.toString())==null){
            toast("La recepta no té més informació")
        }
        else {
            intent = Intent(this, Recepta::class.java)
            startActivity(intent)
        }
    }

    fun recepta2(view: View){
        if (controlador.getReceptaByName(dinar.text.toString())==null){
            toast("La recepta no té més informació")
        }
        else {
            intent = Intent(this, Recepta::class.java)
            startActivity(intent)
        }
    }

    fun recepta3(view: View){
        if (controlador.getReceptaByName(sopar.text.toString())==null){
            toast("La recepta no té més informació")
        }
        else {
            intent = Intent(this, Recepta::class.java)
            startActivity(intent)
        }
    }

    /*
    * Segons la icona clicada s'afegeix el plat en el dia i àpat adequats
     */
    fun escollirDiaIApat(id: Int) {
        when (id) {
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

}


