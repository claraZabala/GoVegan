package com.example.govegan.vista

import android.os.Bundle
import android.text.Editable
import android.view.View
import android.widget.*
import android.widget.AdapterView.OnItemSelectedListener
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.example.govegan.model.Pregunta
import com.example.govegan.model.Resposta
import com.example.govegan.model.Usuari
import kotlinx.android.synthetic.main.afegir_proposta.*
import kotlinx.android.synthetic.main.dialog_ingredients.view.*
import kotlinx.android.synthetic.main.dialog_resposta.view.*
import kotlinx.android.synthetic.main.forum.*
import kotlinx.android.synthetic.main.forum.view.*

class Forum : AppCompatActivity() {
    var llistaPreguntes: ArrayList<String> = ArrayList()
    var controlador: Controlador = Controlador
    var tema: String = ""

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

        //Inicialitzem el tema per defecte perque no calgui escollir inicialment
        tema = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();

        /*/????????????????????????
        //var textV: ScrollView = findViewById(R.id.pregiresp)
        llistaPreguntes = controlador.mostrarPreguntesPerTema(tema)!!
        for (i in llistaPreguntes) {
            var lay: LinearLayout = LinearLayout(this)
            lay.minimumHeight = 60
            lay.minimumWidth = 320

            // Text View Usuari
            var textView: TextView = TextView(this)
            textView.width = 65
            textView.height = 30
            textView.text = controlador.getUsuariActiu()
            lay.addView(textView)

            //Text View descripcio
            var textViewDesc: TextView = TextView(this)
            textViewDesc.width = 170
            textViewDesc.height = 38
            textViewDesc.text = i + controlador.getContadorPreguntes(i, tema)
            lay.addView(textViewDesc)

            //Boto inicialitzat fora per poder fer mètodes de clicar
            var botoRespostes: Button = Button(this)
            botoRespostes.width = 52
            botoRespostes.height = 45
            lay.addView(botoRespostes)

            textV.addView(lay)
        }
        */

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
                tema = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                println("tema = " + tema)
                // Mostrem les preguntes inicialment segons el tema seleccionat
                        llistaPreguntes =
                            controlador.mostrarPreguntesPerTema(tema)!!
                        for (i in llistaPreguntes) {
                            //TODO: Afegit la pregunta a la text view però abans comprovar lo anterior
                        }

                // Que passa quan cliquem al botó crear pregunta
                sendPregunta.setOnClickListener {
                    var descripcio: String = preguntaVew.text.toString()
                    controlador.crearPregunta(descripcio, tema)
                    preguntaVew.setText(" ")
                            //Cal tornar a cridar al mètode de controlador per actualitzar les preguntes
                            llistaPreguntes =
                                controlador.mostrarPreguntesPerTema(tema)!!
                            // TODO: fer que la pregunta s'afegeixi també a la text view

                        }


                        /* Mirrar perque igual aquests mètodes han d'anar fora del override però dins de la classe
                            // Que passa quan cliquem als botons creapts per cada pregunta:
                            anarAResposta.setOnClickListener() {
                                val dialog = AlertDialog.Builder(this)
                                val dialogView = layoutInflater.inflate(R.layout.dialog_resposta, null)
                                // TODO: Fer bé l'intent
                                dialog.setView(dialogView)
                                dialog.setCancelable(false)
                                val mAlertDialog = dialog.show()
                                val customDialog = dialog.create()
                                var tema:String = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
                                //actualitzarLlistaRespostes(dialogView, tema)

                                dialogView.enviarRespostaAlForumm.setOnClickListener {
                                    mAlertDialog.dismiss()
                                }
                            }
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
    }





