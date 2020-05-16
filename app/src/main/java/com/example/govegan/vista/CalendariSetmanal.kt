package com.example.govegan.vista

import android.annotation.SuppressLint
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


@RequiresApi(Build.VERSION_CODES.O)
class CalendariSetmanal : AppCompatActivity() {
    var controlador: Controlador = Controlador

    //quan s'inicialitza l'app es carrega la setmana actual, que ha de canviar cada setmana
    private var setmanaActual: Int
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
                "0" -> {
                    iconaVella.setImageResource(R.drawable.icono)
                    iconaVella.contentDescription = "Vegà"
                }
                "1" -> {
                    iconaVella.setImageResource(R.drawable.ou)
                    iconaVella.contentDescription = "Derivat d'animals"
                }
                "2" -> {
                    iconaVella.setImageResource(R.drawable.carn)
                    iconaVella.contentDescription = "Conté carn"
                }
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
    private fun afegirPlat(im: ImageView) {
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
/*
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
*/
    fun mesInfo(nom: String) {
        if (controlador.getReceptaByName(nom) == null){
            toast("La recepta no té més informació")
        }
        else {
            controlador.setReceptaActiva(nom)
            intent = Intent(this, Recepta::class.java)
            startActivity(intent)
        }
    }
    /*
    * Segons la icona clicada s'afegeix el plat en el dia i àpat adequats
     */
    fun escollirDiaIApat(id: Int,categoria:String?) {
    val s = spinner2.selectedItem.toString()
        when (id) {
            R.id.int1 -> controlador.setDiaRecepta("dilluns", "esmorzar",s,categoria)
            R.id.int2 -> controlador.setDiaRecepta("dimarts", "esmorzar",s,categoria)
            R.id.int3 -> controlador.setDiaRecepta("dimecres", "esmorzar",s,categoria)
            R.id.int4 -> controlador.setDiaRecepta("dijous","esmorzar",s,categoria)
            R.id.int5 -> controlador.setDiaRecepta("divendres","esmorzar",s,categoria)
            R.id.int6 -> controlador.setDiaRecepta("dissabte","esmorzar",s,categoria)
            R.id.int7 -> controlador.setDiaRecepta("diumenge","esmorzar",s,categoria)
            R.id.int8 -> controlador.setDiaRecepta("dilluns","dinar",s,categoria)
            R.id.int9 -> controlador.setDiaRecepta("dimarts","dinar",s,categoria)
            R.id.int10 -> controlador.setDiaRecepta("dimecres","dinar",s,categoria)
            R.id.int11 -> controlador.setDiaRecepta("dijous","dinar",s,categoria)
            R.id.int12 -> controlador.setDiaRecepta("divendres","dinar",s,categoria)
            R.id.int13 -> controlador.setDiaRecepta("dissabte","dinar",s,categoria)
            R.id.int14 -> controlador.setDiaRecepta("diumenge","dinar",s,categoria)
            R.id.int15 -> controlador.setDiaRecepta("dilluns","sopar",s,categoria)
            R.id.int16 -> controlador.setDiaRecepta("dimarts","sopar",s,categoria)
            R.id.int17 -> controlador.setDiaRecepta("dimecres","sopar",s,categoria)
            R.id.int18 -> controlador.setDiaRecepta("dijous","sopar",s,categoria)
            R.id.int19 -> controlador.setDiaRecepta("divendres","sopar",s,categoria)
            R.id.int20 -> controlador.setDiaRecepta("dissabte","sopar",s,categoria)
            R.id.int21 -> controlador.setDiaRecepta("diumenge","sopar",s,categoria)
        }
    }

    fun inicialitzarIconesCalendari(){
        val s = spinner2.selectedItem.toString()
        canviIcona(int1,controlador.getCategoriaApatDia(s,"dilluns","esmorzar"))
        canviIcona(int2,controlador.getCategoriaApatDia(s,"dimarts","esmorzar"))
        canviIcona(int3,controlador.getCategoriaApatDia(s,"dimecres","esmorzar"))
        canviIcona(int4,controlador.getCategoriaApatDia(s,"dijous","esmorzar"))
        canviIcona(int5,controlador.getCategoriaApatDia(s,"divendres","esmorzar"))
        canviIcona(int6,controlador.getCategoriaApatDia(s,"dissabte","esmorzar"))
        canviIcona(int7,controlador.getCategoriaApatDia(s,"diumenge","esmorzar"))
        canviIcona(int8,controlador.getCategoriaApatDia(s,"dilluns","dinar"))
        canviIcona(int9,controlador.getCategoriaApatDia(s,"dimarts","dinar"))
        canviIcona(int10,controlador.getCategoriaApatDia(s,"dimecres","dinar"))
        canviIcona(int11,controlador.getCategoriaApatDia(s,"dijous","dinar"))
        canviIcona(int12,controlador.getCategoriaApatDia(s,"divendres","dinar"))
        canviIcona(int13,controlador.getCategoriaApatDia(s,"dissabte","dinar"))
        canviIcona(int14,controlador.getCategoriaApatDia(s,"diumenge","dinar"))
        canviIcona(int15,controlador.getCategoriaApatDia(s,"dilluns","sopar"))
        canviIcona(int16,controlador.getCategoriaApatDia(s,"dimarts","sopar"))
        canviIcona(int17,controlador.getCategoriaApatDia(s,"dimecres","sopar"))
        canviIcona(int18,controlador.getCategoriaApatDia(s,"dijous","sopar"))
        canviIcona(int19,controlador.getCategoriaApatDia(s,"divendres","sopar"))
        canviIcona(int20,controlador.getCategoriaApatDia(s,"dissabte","sopar"))
        canviIcona(int21,controlador.getCategoriaApatDia(s,"diumenge","sopar"))
    }

    /*
    * Quan es prem mostrar menús, es fa un recorregut pels dies de la setmana activa de l'usuari actiu
    * i s'imprimeixen els menús en TextViews a sota en un scrollView
     */
    @SuppressLint("ResourceType")
    fun mostrarMenus(view: View): Int {
        if (!mostrarMenus.isChecked) {
            //netegem el que hi havia
            layoutMostrarMenus.removeAllViews()
            return 0
        }

        //netegem el que hi havia
        layoutMostrarMenus.removeAllViews()

        /*
        * Creem els LinearLayout per a cada dia de la setmana amb les característiques desitjades
         */
        val layoutDia1 = LinearLayout(this)
        val layoutDia2 = LinearLayout(this)
        val layoutDia3 = LinearLayout(this)
        val layoutDia4 = LinearLayout(this)
        val layoutDia5 = LinearLayout(this)
        val layoutDia6 = LinearLayout(this)
        val layoutDia7 = LinearLayout(this)
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
        val titol1 = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol1.layoutParams = params
        titol1.text = "MENÚ DIA 1"
        layoutDia1.addView(titol1)

        val titol2 = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol2.layoutParams = params
        titol2.text = "MENÚ DIA 2"
        layoutDia2.addView(titol2)

        val titol3 = TextView(this)
        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        titol3.layoutParams = params
        titol3.text = "MENÚ DIA 3"
        layoutDia3.addView(titol3)

        val titol4 = TextView(this)
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

        //DIA 1
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

        val apat1: TextView = TextView(this)
        val apat2: TextView = TextView(this)
        val apat3: TextView = TextView(this)
        val nom1: TextView = TextView(this)
        val nom2: TextView = TextView(this)
        val nom3: TextView = TextView(this)
        val mesInfo1: Button = Button(this)
        val mesInfo2: Button = Button(this)
        val mesInfo3: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
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
        nom1.text = ""
        nom2.text = ""
        nom3.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo1.layoutParams = params
        mesInfo1.text = "+INFO"
        mesInfo1.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo2.layoutParams = params
        mesInfo2.text = "+INFO"
        mesInfo2.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo3.layoutParams = params
        mesInfo3.text = "+INFO"
        mesInfo3.background = resources.getDrawable(R.drawable.estil_botons_2)



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

        val apat21: TextView = TextView(this)
        val apat22: TextView = TextView(this)
        val apat23: TextView = TextView(this)
        val nom21: TextView = TextView(this)
        val nom22: TextView = TextView(this)
        val nom23: TextView = TextView(this)
        val mesInfo21: Button = Button(this)
        val mesInfo22: Button = Button(this)
        val mesInfo23: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
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
        nom21.text = ""
        nom22.text = ""
        nom23.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo21.layoutParams = params
        mesInfo21.text = "+INFO"
        mesInfo21.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo22.layoutParams = params
        mesInfo22.text = "+INFO"
        mesInfo22.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo23.layoutParams = params
        mesInfo23.text = "+INFO"
        mesInfo23.background = resources.getDrawable(R.drawable.estil_botons_2)



        //DIA 3
        val layoutI31: LinearLayout = LinearLayout(this)
        val layoutI32: LinearLayout = LinearLayout(this)
        val layoutI33: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI31.layoutParams = params
        layoutI32.layoutParams = params
        layoutI33.layoutParams = params
        layoutI31.orientation = LinearLayout.HORIZONTAL
        layoutI32.orientation = LinearLayout.HORIZONTAL
        layoutI33.orientation = LinearLayout.HORIZONTAL

        val apat31: TextView = TextView(this)
        val apat32: TextView = TextView(this)
        val apat33: TextView = TextView(this)
        val nom31: TextView = TextView(this)
        val nom32: TextView = TextView(this)
        val nom33: TextView = TextView(this)
        val mesInfo31: Button = Button(this)
        val mesInfo32: Button = Button(this)
        val mesInfo33: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat31.layoutParams = params
        apat32.layoutParams = params
        apat33.layoutParams = params
        apat31.text = "Esmorzar: "
        apat32.text = "Dinar: "
        apat33.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom31.layoutParams = params
        nom32.layoutParams = params
        nom33.layoutParams = params
        nom31.text = ""
        nom32.text = ""
        nom33.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo31.layoutParams = params
        mesInfo31.text = "+INFO"
        mesInfo31.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo32.layoutParams = params
        mesInfo32.text = "+INFO"
        mesInfo32.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo33.layoutParams = params
        mesInfo33.text = "+INFO"
        mesInfo33.background = resources.getDrawable(R.drawable.estil_botons_2)


        //DIA 4
        val layoutI41: LinearLayout = LinearLayout(this)
        val layoutI42: LinearLayout = LinearLayout(this)
        val layoutI43: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI41.layoutParams = params
        layoutI42.layoutParams = params
        layoutI43.layoutParams = params
        layoutI41.orientation = LinearLayout.HORIZONTAL
        layoutI42.orientation = LinearLayout.HORIZONTAL
        layoutI43.orientation = LinearLayout.HORIZONTAL

        val apat41: TextView = TextView(this)
        val apat42: TextView = TextView(this)
        val apat43: TextView = TextView(this)
        val nom41: TextView = TextView(this)
        val nom42: TextView = TextView(this)
        val nom43: TextView = TextView(this)
        val mesInfo41: Button = Button(this)
        val mesInfo42: Button = Button(this)
        val mesInfo43: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat41.layoutParams = params
        apat42.layoutParams = params
        apat43.layoutParams = params
        apat41.text = "Esmorzar: "
        apat42.text = "Dinar: "
        apat43.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom41.layoutParams = params
        nom42.layoutParams = params
        nom43.layoutParams = params
        nom41.text = ""
        nom42.text = ""
        nom43.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo41.layoutParams = params
        mesInfo41.text = "+INFO"
        mesInfo41.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo42.layoutParams = params
        mesInfo42.text = "+INFO"
        mesInfo42.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo43.layoutParams = params
        mesInfo43.text = "+INFO"
        mesInfo43.background = resources.getDrawable(R.drawable.estil_botons_2)

        //DIA 5
        val layoutI51: LinearLayout = LinearLayout(this)
        val layoutI52: LinearLayout = LinearLayout(this)
        val layoutI53: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI51.layoutParams = params
        layoutI52.layoutParams = params
        layoutI53.layoutParams = params
        layoutI51.orientation = LinearLayout.HORIZONTAL
        layoutI52.orientation = LinearLayout.HORIZONTAL
        layoutI53.orientation = LinearLayout.HORIZONTAL

        val apat51: TextView = TextView(this)
        val apat52: TextView = TextView(this)
        val apat53: TextView = TextView(this)
        val nom51: TextView = TextView(this)
        val nom52: TextView = TextView(this)
        val nom53: TextView = TextView(this)
        val mesInfo51: Button = Button(this)
        val mesInfo52: Button = Button(this)
        val mesInfo53: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat51.layoutParams = params
        apat52.layoutParams = params
        apat53.layoutParams = params
        apat51.text = "Esmorzar: "
        apat52.text = "Dinar: "
        apat53.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom51.layoutParams = params
        nom52.layoutParams = params
        nom53.layoutParams = params
        nom51.text = ""
        nom52.text = ""
        nom53.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo51.layoutParams = params
        mesInfo51.text = "+INFO"
        mesInfo51.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo52.layoutParams = params
        mesInfo52.text = "+INFO"
        mesInfo52.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo53.layoutParams = params
        mesInfo53.text = "+INFO"
        mesInfo53.background = resources.getDrawable(R.drawable.estil_botons_2)

        //DIA 6
        val layoutI61: LinearLayout = LinearLayout(this)
        val layoutI62: LinearLayout = LinearLayout(this)
        val layoutI63: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI61.layoutParams = params
        layoutI62.layoutParams = params
        layoutI63.layoutParams = params
        layoutI61.orientation = LinearLayout.HORIZONTAL
        layoutI62.orientation = LinearLayout.HORIZONTAL
        layoutI63.orientation = LinearLayout.HORIZONTAL

        val apat61: TextView = TextView(this)
        val apat62: TextView = TextView(this)
        val apat63: TextView = TextView(this)
        val nom61: TextView = TextView(this)
        val nom62: TextView = TextView(this)
        val nom63: TextView = TextView(this)
        val mesInfo61: Button = Button(this)
        val mesInfo62: Button = Button(this)
        val mesInfo63: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat61.layoutParams = params
        apat62.layoutParams = params
        apat63.layoutParams = params
        apat61.text = "Esmorzar: "
        apat62.text = "Dinar: "
        apat63.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom61.layoutParams = params
        nom62.layoutParams = params
        nom63.layoutParams = params
        nom61.text = ""
        nom62.text = ""
        nom63.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo61.layoutParams = params
        mesInfo61.text = "+INFO"
        mesInfo61.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo62.layoutParams = params
        mesInfo62.text = "+INFO"
        mesInfo62.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo63.layoutParams = params
        mesInfo63.text = "+INFO"
        mesInfo63.background = resources.getDrawable(R.drawable.estil_botons_2)

        //DIA 7
        val layoutI71: LinearLayout = LinearLayout(this)
        val layoutI72: LinearLayout = LinearLayout(this)
        val layoutI73: LinearLayout = LinearLayout(this)

        params = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            100
        )
        layoutI71.layoutParams = params
        layoutI72.layoutParams = params
        layoutI73.layoutParams = params
        layoutI71.orientation = LinearLayout.HORIZONTAL
        layoutI72.orientation = LinearLayout.HORIZONTAL
        layoutI73.orientation = LinearLayout.HORIZONTAL

        val apat71: TextView = TextView(this)
        val apat72: TextView = TextView(this)
        val apat73: TextView = TextView(this)
        val nom71: TextView = TextView(this)
        val nom72: TextView = TextView(this)
        val nom73: TextView = TextView(this)
        val mesInfo71: Button = Button(this)
        val mesInfo72: Button = Button(this)
        val mesInfo73: Button = Button(this)
        params = LinearLayout.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        apat71.layoutParams = params
        apat72.layoutParams = params
        apat73.layoutParams = params
        apat71.text = "Esmorzar: "
        apat72.text = "Dinar: "
        apat73.text = "Sopar: "


        params = LinearLayout.LayoutParams(
            229,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )

        nom71.layoutParams = params
        nom72.layoutParams = params
        nom73.layoutParams = params
        nom71.text = ""
        nom72.text = ""
        nom73.text = ""

        params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        mesInfo71.layoutParams = params
        mesInfo71.text = "+INFO"
        mesInfo71.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo72.layoutParams = params
        mesInfo72.text = "+INFO"
        mesInfo72.background = resources.getDrawable(R.drawable.estil_botons_2)
        mesInfo73.layoutParams = params
        mesInfo73.text = "+INFO"
        mesInfo73.background = resources.getDrawable(R.drawable.estil_botons_2)

        //s'omplen els textViews amb els menús de l'usuari actual
        val noms: List<TextView> = listOf(nom1, nom2, nom3, nom21, nom22, nom23, nom31,
            nom32, nom33, nom41, nom42, nom43, nom51, nom52, nom53, nom61, nom62, nom63, nom71,
            nom72, nom73)

        recorregutMenus(noms)

        layoutI11.addView(apat1)
        layoutI11.addView(nom1)
        layoutI11.addView(mesInfo1)
        layoutI21.addView(apat21)
        layoutI21.addView(nom21)
        layoutI21.addView(mesInfo21)
        layoutI31.addView(apat31)
        layoutI31.addView(nom31)
        layoutI31.addView(mesInfo31)
        layoutI41.addView(apat41)
        layoutI41.addView(nom41)
        layoutI41.addView(mesInfo41)
        layoutI51.addView(apat51)
        layoutI51.addView(nom51)
        layoutI51.addView(mesInfo51)
        layoutI61.addView(apat61)
        layoutI61.addView(nom61)
        layoutI61.addView(mesInfo61)
        layoutI71.addView(apat71)
        layoutI71.addView(nom71)
        layoutI71.addView(mesInfo71)

        layoutI12.addView(apat2)
        layoutI12.addView(nom2)
        layoutI12.addView(mesInfo2)
        layoutI22.addView(apat22)
        layoutI22.addView(nom22)
        layoutI22.addView(mesInfo22)
        layoutI32.addView(apat32)
        layoutI32.addView(nom32)
        layoutI32.addView(mesInfo32)
        layoutI42.addView(apat42)
        layoutI42.addView(nom42)
        layoutI42.addView(mesInfo42)
        layoutI52.addView(apat52)
        layoutI52.addView(nom52)
        layoutI52.addView(mesInfo52)
        layoutI62.addView(apat62)
        layoutI62.addView(nom62)
        layoutI62.addView(mesInfo62)
        layoutI72.addView(apat72)
        layoutI72.addView(nom72)
        layoutI72.addView(mesInfo72)

        layoutI13.addView(apat3)
        layoutI13.addView(nom3)
        layoutI13.addView(mesInfo3)
        layoutI23.addView(apat23)
        layoutI23.addView(nom23)
        layoutI23.addView(mesInfo23)
        layoutI33.addView(apat33)
        layoutI33.addView(nom33)
        layoutI33.addView(mesInfo33)
        layoutI43.addView(apat43)
        layoutI43.addView(nom43)
        layoutI43.addView(mesInfo43)
        layoutI53.addView(apat53)
        layoutI53.addView(nom53)
        layoutI53.addView(mesInfo53)
        layoutI63.addView(apat63)
        layoutI63.addView(nom63)
        layoutI63.addView(mesInfo63)
        layoutI73.addView(apat73)
        layoutI73.addView(nom73)
        layoutI73.addView(mesInfo73)

        layoutDia1.addView(layoutI11)
        layoutDia1.addView(layoutI12)
        layoutDia1.addView(layoutI13)
        layoutDia2.addView(layoutI21)
        layoutDia2.addView(layoutI22)
        layoutDia2.addView(layoutI23)
        layoutDia3.addView(layoutI31)
        layoutDia3.addView(layoutI32)
        layoutDia3.addView(layoutI33)
        layoutDia4.addView(layoutI41)
        layoutDia4.addView(layoutI42)
        layoutDia4.addView(layoutI43)
        layoutDia5.addView(layoutI51)
        layoutDia5.addView(layoutI52)
        layoutDia5.addView(layoutI53)
        layoutDia6.addView(layoutI61)
        layoutDia6.addView(layoutI62)
        layoutDia6.addView(layoutI63)
        layoutDia7.addView(layoutI71)
        layoutDia7.addView(layoutI72)
        layoutDia7.addView(layoutI73)

        //enganxa els layouts de cada dia al contenidor
        layoutMostrarMenus.addView(layoutDia1)
        layoutMostrarMenus.addView(layoutDia2)
        layoutMostrarMenus.addView(layoutDia3)
        layoutMostrarMenus.addView(layoutDia4)
        layoutMostrarMenus.addView(layoutDia5)
        layoutMostrarMenus.addView(layoutDia6)
        layoutMostrarMenus.addView(layoutDia7)

        //quan es premi els botons de +info es farà un intent cap a la recepta o be un missatge
        //toast si no és disponible
        val llistaBotons: List<Button> = listOf(mesInfo1, mesInfo2, mesInfo3, mesInfo21, mesInfo22,
            mesInfo23, mesInfo31, mesInfo32, mesInfo33, mesInfo41, mesInfo42, mesInfo43, mesInfo51,
            mesInfo52, mesInfo53, mesInfo61, mesInfo62, mesInfo63, mesInfo71, mesInfo72, mesInfo73)
        for (i in 0..20) {
            llistaBotons[i].setOnClickListener {
                val nom = noms[i].text.toString()
                mesInfo(nom)
            }
        }


        return 0
    }


    /*
    * Actualitza els noms dels menús de la setmana actual en l'ScrollView
     */
    private fun recorregutMenus(noms: List<TextView>) {
        //agafem la setmana que es troba actualment a l'spinner
        val setmana = spinner2.selectedItem.toString().takeLastWhile { it.isDigit() }
        val llistaMenus = controlador.recorrerMenus(setmana.toInt())
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
                                1 -> noms[0].text = i
                                2 -> noms[1].text = i
                                3 -> noms[2].text = i
                            }
                        }
                        2 -> {
                            when (apats) {
                                1 -> noms[3].text = i
                                2 -> noms[4].text = i
                                3 -> noms[5].text = i
                            }
                        }
                        3 -> {
                            when (apats) {
                                1 -> noms[6].text = i
                                2 -> noms[7].text = i
                                3 -> noms[8].text = i
                            }
                        }
                        4 -> {
                            when (apats) {
                                1 -> noms[9].text = i
                                2 -> noms[10].text = i
                                3 -> noms[11].text = i
                            }
                        }
                        5 -> {
                            when (apats) {
                                1 -> noms[12].text = i
                                2 -> noms[14].text = i
                                3 -> noms[14].text = i
                            }
                        }
                        6 -> {
                            when (apats) {
                                1 -> noms[15].text = i
                                2 -> noms[16].text = i
                                3 -> noms[17].text = i
                            }
                        }
                        7 -> {
                            when (apats) {
                                1 -> noms[18].text = i
                                2 -> noms[19].text = i
                                3 -> noms[20].text = i
                            }
                        }

                    }

                }

            }
        }
    }

}


