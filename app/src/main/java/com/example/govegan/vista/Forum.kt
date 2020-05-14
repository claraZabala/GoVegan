package com.example.govegan.vista

import android.os.Build
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.example.govegan.model.Pregunta
import kotlinx.android.synthetic.main.dialog_resposta.*
import kotlinx.android.synthetic.main.dialog_resposta.view.*
import kotlinx.android.synthetic.main.forum.*
import kotlinx.android.synthetic.main.forum.preguntaVew


class Forum : AppCompatActivity() {
    var llistaPreguntes: ArrayList<String> = ArrayList()
    var controlador: Controlador = Controlador
    var tema: String = ""

    @RequiresApi(Build.VERSION_CODES.O)
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


        spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onNothingSelected(parent: AdapterView<*>?) {
                toast("Selecciona un tema")
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                var layoutpreg: LinearLayout = findViewById(R.id.layoutpreg)
                layoutpreg.removeAllViews()
                tema = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                println("tema = " + tema)
                // Mostrem les preguntes inicialment segons el tema seleccionat
                llistaPreguntes =
                    controlador.mostrarPreguntesPerTema(tema)!!
                for (i in llistaPreguntes) {
                    mostrarPrgeunta(i, layoutpreg)
                }

                // Que passa quan cliquem al botó crear pregunta
                sendPregunta.setOnClickListener {
                    var descripcio: String = preguntaVew.text.toString()
                    controlador.crearPregunta(descripcio, tema)
                    preguntaVew.setText(" ")
                    //Cal tornar a cridar al mètode de controlador per actualitzar les preguntes
                        mostrarPrgeunta(descripcio, layoutpreg)
                }


                /*
                    // Que passa quan cliquem als botons creapts per cada pregunta:

                }


                fun actualitzarLlistaRespostes(dialogView: View, tema: String){
                    dialogView.layoutIngredientsBD.removeAllViews()
                    for (i in llistaIngredients) {
                        var btnIngredient: CheckBox = CheckBox(this)
                        btnIngredient.setText(i)
                        dialogView.layoutIngredientsBD.addView(btnIngredient)
                        if (i in llistaIngredientsCompra)
                            btnIngredient.isChecked = true
                        btnIngredient.setOnClickListener {
                            if (btnIngredient.isChecked) {
                                llistaIngredientsCompra.add(btnIngredient.text.toString())
                                textIngredients.text = llistaIngredientsCompra.toString()
                            }
                            if (!btnIngredient.isChecked) {
                                llistaIngredientsCompra.remove(btnIngredient.text.toString())
                                textIngredients.text = llistaIngredientsCompra.toString()
                            }
                        }
                    }*/
            }
        }
    }

    fun mostrarPrgeunta(i : String, layoutpreg : LinearLayout){
        var layoutpreg: LinearLayout = findViewById(R.id.layoutpreg)
        var params:ViewGroup.LayoutParams
        var lay: LinearLayout = LinearLayout(this)
        lay.orientation = LinearLayout.HORIZONTAL

        // Text View Usuari
        var textView: TextView = TextView(this)
        params = ViewGroup.LayoutParams(
            250,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textView.layoutParams = params
        textView.text = controlador.getUsuari(i)
        textView.gravity = Gravity.LEFT
        lay.addView(textView)

        //Text View descripcio
        var textViewDesc: TextView = TextView(this)
        textViewDesc.text = i +  " Respostes: " + controlador.getContadorPreguntes(i, tema)
        textViewDesc.gravity = Gravity.CENTER
        params = ViewGroup.LayoutParams(
            670,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        textViewDesc.layoutParams = params
        lay.addView(textViewDesc)

        //Boto inicialitzat fora per poder fer mètodes de clicar
        var botoRespostes: Button = Button(this)
        params = ViewGroup.LayoutParams(
            180,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        botoRespostes.layoutParams = params
        botoRespostes.text = "respostes"
        botoRespostes.setBackgroundColor(getResources().getColor(R.color.verdClar))
        botoRespostes.gravity = Gravity.CENTER
        lay.addView(botoRespostes)
        params = ViewGroup.LayoutParams(
            ViewGroup.LayoutParams.WRAP_CONTENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        lay.layoutParams = params


        layoutpreg.addView(lay)

        botoRespostes.setOnClickListener() {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_resposta, null)
            dialog.setView(dialogView)
            dialog.setCancelable(false)
            val mAlertDialog = dialog.show()
            val customDialog = dialog.create()
            dialogView.enviarRespostaAlForumm.setOnClickListener {

            }

            dialogView.buttonEnrere.setOnClickListener {
                mAlertDialog.dismiss()
            }
        }

    }
}





