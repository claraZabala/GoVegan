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
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.forum.*


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

        //Inicialitzem el tema per defecte perque no calgui escollir inicialment
        tema = spinner.getItemAtPosition(spinner.getSelectedItemPosition()).toString();
        var layoutpreg: LinearLayout = findViewById(R.id.layoutpreg)
        var params:ViewGroup.LayoutParams
        llistaPreguntes = controlador.mostrarPreguntesPerTema(tema)!!
        for (i in llistaPreguntes) {

            var lay: LinearLayout = LinearLayout(this)
            lay.orientation = LinearLayout.HORIZONTAL
            // Text View Usuari

            var textView: TextView = TextView(this)
            params = ViewGroup.LayoutParams(
                250,
                ViewGroup.LayoutParams.WRAP_CONTENT
            )
            textView.layoutParams = params
            textView.text = controlador.getUsuariActiu()
            textView.gravity = Gravity.LEFT
            lay.addView(textView)

            //Text View descripcio
            var textViewDesc: TextView = TextView(this)
            textViewDesc.text = i + controlador.getContadorPreguntes(i, tema)
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
        }

        /*
        <Button
        android:id="@+id/anarAResposta"
        android:layout_width="52dp"
        android:layout_height="wrap_content"
        android:layout_margin="5dp"
        android:background="@color/verdClar"
        android:text="respostes"></Button>

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





