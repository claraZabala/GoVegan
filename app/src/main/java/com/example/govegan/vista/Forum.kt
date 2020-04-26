package com.example.govegan.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.dialog_resposta.view.*
import kotlinx.android.synthetic.main.forum.*
import kotlinx.android.synthetic.main.forum.view.*

class Forum : AppCompatActivity() {
    var llistaPreguntesReceptes:ArrayList<String> = ArrayList()
    var llistaPreguntesonCompro:ArrayList<String> = ArrayList()
    var llistaPreguntesdietes:ArrayList<String> = ArrayList()
    var llistaPreguntesrestaurants:ArrayList<String> = ArrayList()
    var llistaPreguntespropietats:ArrayList<String> = ArrayList()
    var llistaPreguntesmoda:ArrayList<String> = ArrayList()
    var llistaPregunteshigiene:ArrayList<String> = ArrayList()
    var llistaPreguntessalut:ArrayList<String> = ArrayList()
    var llistaPreguntesaltres:ArrayList<String> = ArrayList()
    var controlador:Controlador = Controlador

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.forum)
            llistaPreguntesReceptes = controlador.mostrarPreguntesPerTema("Receptes")!!
            llistaPreguntesonCompro =
                controlador.mostrarPreguntesPerTema("On compro els ingredients?")!!
            llistaPreguntesdietes = controlador.mostrarPreguntesPerTema("Dietes")!!
            llistaPreguntesrestaurants = controlador.mostrarPreguntesPerTema("Restaurants")!!
            llistaPreguntespropietats = controlador.mostrarPreguntesPerTema("Propietats")!!
            llistaPreguntesmoda = controlador.mostrarPreguntesPerTema("Moda")!!
            llistaPregunteshigiene = controlador.mostrarPreguntesPerTema("Higiene")!!
            llistaPreguntessalut = controlador.mostrarPreguntesPerTema("Salut")!!
            llistaPreguntesaltres = controlador.mostrarPreguntesPerTema("Altres")!!

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
                spinner.adapter = adapter
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

            anarAResposta.setOnClickListener {
                val dialog = AlertDialog.Builder(this)
                val dialogView = layoutInflater.inflate(R.layout.dialog_resposta, null)
                dialog.setView(dialogView)
                dialog.setCancelable(false)
                val mAlertDialog = dialog.show()
                val customDialog = dialog.create()
                actualitzarLlistaRespostes(dialogView)

                dialogView.enviarRespostaAlForumm.setOnClickListener {
                    mAlertDialog.dismiss()
                }
                dialogView.sendPregunta.setOnClickListener {
                    val tema:String = spinner.getItemAtPosition(spinner.selectedItemPosition).toString()
                    val descripcio : String = preguntaVew.text.toString()
                    if (tema != null){
                        afegirNovaPregunta(dialogView,descripcio, tema)
                        preguntaVew.setText(" ")
                    }else{
                        toast("Selecciona un tema")
                }

                }

            }

        }
        fun actualitzarLlistaRespostes(dialogView: View){
            // No entenc com funciona la actualitzarIngredients de la classe java AfegirProposta
        }

        fun afegirNovaPregunta(dialogView: View, descripcio: String, tema: String){
            controlador.crearPregunta(descripcio, tema)
            // TODO: fer que la pregunta s'afegeixi també a la text view
        }
    }

