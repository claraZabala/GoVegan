package com.example.govegan.vista

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
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
import kotlin.collections.ArrayList


@RequiresApi(Build.VERSION_CODES.O)
class CalendariSetmanal : AppCompatActivity() {
    var controlador: Controlador
    init {
        controlador = Controlador
    }

    //quan s'inicialitza l'app es carrega la setmana actual, que ha de canviar cada setmana
    var setmanaActual: Int
    init {
        setmanaActual = controlador.getSetmanaActual()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendari_setmanal)


        //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
        //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
        //val spinner: Spinner = spinner2
        // Create an ArrayAdapter using the string array and a default spinner layout

        ArrayAdapter.createFromResource(
            this,
            R.array.setmanes,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner2.adapter = adapter
            spinner2.adapter = adapter
            spinner2.setSelection(setmanaActual-1)
            spinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    inicialitzarIconesCalendari()
                    Toast.makeText(
                        adapterView.context,
                        adapterView.getItemAtPosition(pos) as String, Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }
        }

        /*
        * Si es fa dobleclick en una icona, s'obre un dialog per a afegir àpat
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

    fun canviIcona(iconaVella: ImageView, iconaNova: String?) {
        if (iconaNova != null) {
            when (iconaNova) {
                "0" -> iconaVella.setImageResource(R.drawable.icono)
                "1" -> iconaVella.setImageResource(R.drawable.ou)
                "2" -> iconaVella.setImageResource(R.drawable.carn)
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
                                var categoria: String ="0"
                                if (carnivor) categoria = "2"
                                else if (vegetaria) categoria = "1"


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
                            // Controlador el titol de la recepta, tot i que o provingui d'una proposta
                            escollirDiaIApat(im.id,controlador.getIconaReceptaActiva())

                            //es canvia la icona del calendari
                            canviIcona(im, controlador.getIconaReceptaActiva())

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
    fun escollirDiaIApat(id: Int,categoria:String?) {
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

    /*
    * Quan es prem mostrar menús, es fa un recorregut pels dies de la setmana activa de l'usuari actiu
    * i s'imprimeixen els menús en TextViews a sota en un scrollView
     */
    fun mostrarMenus(view: View) {
        //netegem el que hi havia
        layoutMostrarMenus.removeAllViews()

        /*
        * Creem els LinearLayout per a cada dia de la setmana amb les característiques desitjades
         */
        var layoutDia1: LinearLayout = LinearLayout(this)
        var layoutDia2: LinearLayout = LinearLayout(this)
        var layoutDia3: LinearLayout = LinearLayout(this)
        var layoutDia4: LinearLayout = LinearLayout(this)
        var layoutDia5: LinearLayout = LinearLayout(this)
        var layoutDia6: LinearLayout = LinearLayout(this)
        var layoutDia7: LinearLayout = LinearLayout(this)
        layoutDia1.orientation = LinearLayout.VERTICAL
        layoutDia2.orientation = LinearLayout.VERTICAL
        layoutDia3.orientation = LinearLayout.VERTICAL
        layoutDia4.orientation = LinearLayout.VERTICAL
        layoutDia5.orientation = LinearLayout.VERTICAL
        layoutDia6.orientation = LinearLayout.VERTICAL
        layoutDia7.orientation = LinearLayout.VERTICAL
        var params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
        )
        layoutDia1.layoutParams = params
        layoutDia2.layoutParams = params
        layoutDia3.layoutParams = params
        layoutDia4.layoutParams = params
        layoutDia5.layoutParams = params
        layoutDia6.layoutParams = params
        layoutDia7.layoutParams = params

        /*
        * Afegim a cada layout de cada dia el titol MENÚ DIA X
         */
        val titol1: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol1.layoutParams = params
        titol1.text = "MENÚ DIA 1"
        layoutDia1.addView(titol1)

        val titol2: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol2.layoutParams = params
        titol2.text = "MENÚ DIA 2"
        layoutDia2.addView(titol2)

        val titol3: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol3.layoutParams = params
        titol3.text = "MENÚ DIA 3"
        layoutDia3.addView(titol3)

        val titol4: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol4.layoutParams = params
        titol4.text = "MENÚ DIA 4"
        layoutDia4.addView(titol4)

        val titol5: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol5.layoutParams = params
        titol5.text = "MENÚ DIA 5"
        layoutDia5.addView(titol5)

        val titol6: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol6.layoutParams = params
        titol6.text = "MENÚ DIA 6"
        layoutDia6.addView(titol6)

        val titol7: TextView = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol7.layoutParams = params
        titol7.text = "MENÚ DIA 7"
        layoutDia7.addView(titol7)

        val layoutI11: LinearLayout = LinearLayout(this)
        val layoutI12: LinearLayout = LinearLayout(this)
        val layoutI13: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI11.layoutParams = params
        layoutI12.layoutParams = params
        layoutI13.layoutParams = params
        layoutI11.orientation = LinearLayout.HORIZONTAL
        layoutI12.orientation = LinearLayout.HORIZONTAL
        layoutI13.orientation = LinearLayout.HORIZONTAL

        var apat1: TextView = TextView(this)
        var apat2: TextView = TextView(this)
        var apat3: TextView = TextView(this)
        var nom1: TextView = TextView(this)
        var nom2: TextView = TextView(this)
        var nom3: TextView = TextView(this)
        var mesInfo1: Button = Button(this)
        var mesInfo2: Button = Button(this)
        var mesInfo3: Button = Button(this)
        params = LinearLayout.LayoutParams(
            175,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat1.layoutParams = params
        apat2.layoutParams = params
        apat3.layoutParams = params
        apat1.text = "Esmorzar: "
        apat2.text = "Dinar: "
        apat3.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom1.layoutParams = params
        nom2.layoutParams = params
        nom3.layoutParams = params
        nom1.text = "Proposta 1"
        nom2.text = "Proposta 2"
        nom3.text = "Proposta 3"

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo1.layoutParams = params
        mesInfo1.text = "+INFO"
        mesInfo2.layoutParams = params
        mesInfo2.text = "+INFO"
        mesInfo3.layoutParams = params
        mesInfo3.text = "+INFO"

        //DIA 2
        val layoutI21: LinearLayout = LinearLayout(this)
        val layoutI22: LinearLayout = LinearLayout(this)
        val layoutI23: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI21.layoutParams = params
        layoutI22.layoutParams = params
        layoutI23.layoutParams = params
        layoutI21.orientation = LinearLayout.HORIZONTAL
        layoutI22.orientation = LinearLayout.HORIZONTAL
        layoutI23.orientation = LinearLayout.HORIZONTAL

        var apat21: TextView = TextView(this)
        var apat22: TextView = TextView(this)
        var apat23: TextView = TextView(this)
        var nom21: TextView = TextView(this)
        var nom22: TextView = TextView(this)
        var nom23: TextView = TextView(this)
        var mesInfo21: Button = Button(this)
        var mesInfo22: Button = Button(this)
        var mesInfo23: Button = Button(this)
        params = LinearLayout.LayoutParams(
            175,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat21.layoutParams = params
        apat22.layoutParams = params
        apat23.layoutParams = params
        apat21.text = "Esmorzar: "
        apat22.text = "Dinar: "
        apat23.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom21.layoutParams = params
        nom22.layoutParams = params
        nom23.layoutParams = params
        nom21.text = "Proposta 1"
        nom22.text = "Proposta 2"
        nom23.text = "Proposta 3"

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo21.layoutParams = params
        mesInfo21.text = "+INFO"
        mesInfo22.layoutParams = params
        mesInfo22.text = "+INFO"
        mesInfo23.layoutParams = params
        mesInfo23.text = "+INFO"



        val llistaMenus = controlador.recorrerMenus(setmanaActual)
        //es recorre l'array de menús trobats i s'escriuen en els textViews
        var apats: Int = 1
        var dies: Int = 1
        if (llistaMenus != null) {
            for (i in llistaMenus) {
                if (i.equals("-2")) {
                    dies++
                    apats = 1
                }
                else if (i.equals("-1")) apats++
                else {
                    //assignar a nomXY el valor de i
                    when (dies) {
                        1 -> {
                            when (apats) {
                                1 -> nom1.text = i
                                2 -> nom2.text = i
                                3 -> nom3.text = i
                            }
                        }
                        2 -> {
                            when (apats) {
                                1 -> nom21.text = i
                                2 -> nom22.text = i
                                3 -> nom23.text = i
                            }
                        }

                    }
                    println(i)
                }

            }
        }

        layoutI11.addView(apat1)
        layoutI11.addView(nom1)
        layoutI11.addView(mesInfo1)
        layoutI21.addView(apat21)
        layoutI21.addView(nom21)
        layoutI21.addView(mesInfo21)

        layoutI12.addView(apat2)
        layoutI12.addView(nom2)
        layoutI12.addView(mesInfo2)
        layoutI22.addView(apat22)
        layoutI22.addView(nom22)
        layoutI22.addView(mesInfo22)

        layoutI13.addView(apat3)
        layoutI13.addView(nom3)
        layoutI13.addView(mesInfo3)
        layoutI23.addView(apat23)
        layoutI23.addView(nom23)
        layoutI23.addView(mesInfo23)

        layoutDia1.addView(layoutI11)
        layoutDia1.addView(layoutI12)
        layoutDia1.addView(layoutI13)
        layoutDia2.addView(layoutI21)
        layoutDia2.addView(layoutI22)
        layoutDia2.addView(layoutI23)
        /* var arrayLayouts: ArrayList<LinearLayout> = ArrayList()
         arrayLayouts.addAll(listOf(layoutI1, layoutI2, layoutI3, layoutI4, layoutI5, layoutI6, layoutI7))
         for (i in arrayLayouts) {
             var apat1: TextView = TextView(this)
             var apat2: TextView = TextView(this)
             var apat3: TextView = TextView(this)
             var nom: TextView = TextView(this)
             var mesInfo: Button = Button(this)
             params = LinearLayout.LayoutParams(
                 70,
                 ViewGroup.LayoutParams.WRAP_CONTENT
             )
             apat1.layoutParams = params
             apat2.layoutParams = params
             apat3.layoutParams = params

             apat1.text = "Esmorzar"
             apat2.text = "Dinar"
             apat3.text = "Sopar"
             i.addView(apat1)
             i.addView(apat2)
             i.addView(apat3)
         }*/


        //enganxa els layouts de cada dia al contenidor
        layoutMostrarMenus.addView(layoutDia1)
        layoutMostrarMenus.addView(layoutDia2)
        layoutMostrarMenus.addView(layoutDia3)
        layoutMostrarMenus.addView(layoutDia4)
        layoutMostrarMenus.addView(layoutDia5)
        layoutMostrarMenus.addView(layoutDia6)
        layoutMostrarMenus.addView(layoutDia7)


    }

}


