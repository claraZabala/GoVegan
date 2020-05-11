package com.example.govegan.vista

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.calendari_setmanal.*
import kotlinx.android.synthetic.main.dialog_afegir_plat.view.*
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.temporal.TemporalField
import java.time.temporal.WeekFields
import java.util.*


@RequiresApi(Build.VERSION_CODES.O)
class CalendariSetmanal : AppCompatActivity() {
    var controlador: Controlador
    init {
        controlador = Controlador
    }

    //quan s'inicialitza l'app es carrega la setmana actual, que ha de canviar cada setmana
    var setmanaActual: String
    init {
        val date: LocalDate = LocalDate.now()
        val woy: TemporalField = WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()
        val weekNumber: Int = date.get(woy) //Aquesta variable et diu el nombre de setmana de l'any
        //TODO: gestionar com fiquem les setmanes
        print("Week"+weekNumber)
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
        inicialitzarIconesCalendari()



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


    override fun onBackPressed() {
        super.onBackPressed()
        controlador.setIsFromProposta(false)
    }

    fun canviIcona(iconaVella: ImageView, iconaNova: Int?) {
        if (iconaNova != null) {
            when (iconaNova) {
                0 -> iconaVella.setImageResource(R.drawable.icono)
                1 -> iconaVella.setImageResource(R.drawable.ou)
                2 -> iconaVella.setImageResource(R.drawable.carn)
            }
        }
        else{
            iconaVella.setImageResource(R.drawable.interrogacio)
        }
    }

    /*
    * Estableix l'escoltador per quan es fa doble click en una icona.
    * S'obre un AlertDialog on omplir el nom i si és vegà l'àpat i s'afegeix la informació a les
    * dades de l'usuari, a banda de canviar la icona del calendari per la adient.
     */
    fun afegirPlat(im: ImageView) {
        im.setOnClickListener (
            DoubleClickListener(
                callback = object : DoubleClickListener.Callback {
                    override fun doubleClicked() {
                        //s'obre l'AlertDialog per a omplir les dades
                        if(!controlador.getIsFromProposta()){
                            val dialog = AlertDialog.Builder(this@CalendariSetmanal)
                            val dialogView = layoutInflater.inflate(R.layout.dialog_afegir_plat, null)
                            dialog.setView(dialogView)
                            dialog.setCancelable(false)
                            val mAlertDialog = dialog.show()
                            val customDialog = dialog.create()

                            //quan es prem el botò es desen les informacions
                            dialogView.afegirPlatCalendari.setOnClickListener {
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
                                escollirDiaIApat(im.id,categoria)

                                //es canvia la icona del calendari
                                canviIcona(im, categoria)
                                mAlertDialog.dismiss()
                            }
                        }
                        else{
                            //s'afegeix la info a memòria
                            //es reutilitza la funció setReceptaFromProposta per a tenir a
                            // Controlador el titol de la recepta, tot i que o provingui d'un aproposta
                            escollirDiaIApat(im.id,controlador.getReceptaActiva()?.icona)

                            //es canvia la icona del calendari
                            canviIcona(im, controlador.getReceptaActiva()?.icona)

                        }
                    }
            }
        ))
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
    fun escollirDiaIApat(id: Int,categoria:Int?) {
        when (id) {
            R.id.int1 -> controlador.setDiaRecepta("dilluns", "esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int2 -> controlador.setDiaRecepta("dimarts", "esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int3 -> controlador.setDiaRecepta("dimecres", "esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int4 -> controlador.setDiaRecepta("dijous","esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int5 -> controlador.setDiaRecepta("divendres","esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int6 -> controlador.setDiaRecepta("dissabte","esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int7 -> controlador.setDiaRecepta("diumenge","esmorzar",spinner2.selectedItem.toString(),categoria)
            R.id.int8 -> controlador.setDiaRecepta("dilluns","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int9 -> controlador.setDiaRecepta("dimarts","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int10 -> controlador.setDiaRecepta("dimecres","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int11 -> controlador.setDiaRecepta("dijous","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int12 -> controlador.setDiaRecepta("divendres","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int13 -> controlador.setDiaRecepta("dissabte","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int14 -> controlador.setDiaRecepta("diumenge","dinar",spinner2.selectedItem.toString(),categoria)
            R.id.int15 -> controlador.setDiaRecepta("dilluns","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int16 -> controlador.setDiaRecepta("dimarts","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int17 -> controlador.setDiaRecepta("dimecres","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int18 -> controlador.setDiaRecepta("dijous","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int19 -> controlador.setDiaRecepta("divendres","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int20 -> controlador.setDiaRecepta("dissabte","sopar",spinner2.selectedItem.toString(),categoria)
            R.id.int21 -> controlador.setDiaRecepta("diumenge","sopar",spinner2.selectedItem.toString(),categoria)
        }
    }

    fun inicialitzarIconesCalendari(){
        canviIcona(int1,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dilluns","esmorzar"))
        canviIcona(int2,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimarts","esmorzar"))
        canviIcona(int3,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimecres","esmorzar"))
        canviIcona(int4,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dijous","esmorzar"))
        canviIcona(int5,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"divendres","esmorzar"))
        canviIcona(int6,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dissabte","esmorzar"))
        canviIcona(int7,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"diumenge","esmorzar"))
        canviIcona(int8,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dilluns","dinar"))
        canviIcona(int9,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimarts","dinar"))
        canviIcona(int10,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimecres","dinar"))
        canviIcona(int11,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dijous","dinar"))
        canviIcona(int12,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"divendres","dinar"))
        canviIcona(int13,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dissabte","dinar"))
        canviIcona(int14,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"diumenge","dinar"))
        canviIcona(int15,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dilluns","sopar"))
        canviIcona(int16,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimarts","sopar"))
        canviIcona(int17,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dimecres","sopar"))
        canviIcona(int18,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dijous","sopar"))
        canviIcona(int19,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"divendres","sopar"))
        canviIcona(int20,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"dissabte","sopar"))
        canviIcona(int21,controlador.getCategoriaApatDia(spinner2.selectedItem.toString(),"diumenge","sopar"))
    }

}


