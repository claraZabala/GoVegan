package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Ingredient
import kotlinx.android.synthetic.main.afegir_proposta.*
import kotlinx.android.synthetic.main.dialog_ingredients.view.*
import kotlinx.android.synthetic.main.recepta.*

class Recepta : AppCompatActivity() {

    var nom: String = R.id.titol_recepta.toString()
    var tempsPreparacio: Float = R.id.t_prep.toFloat()
    var tempsCuina: Float = R.id.t_cuina.toFloat()
    var numPersones: Int = R.id.comensals
    var descripcio: String = R.id.passos.toString()
    var ingredients: ArrayList<String> = ArrayList()
    var imatge: String = ""
    var llistaIcones = arrayOf(
        "camiIcona1(ex:ous)",
        "camiIcona2(ex:carn)",
        "camiIcona3(ex:caraHappy)",
        "camiIcona4(ex:gluten)"
    )
    var esCertificat = false
    var controlador: Controlador

    init {
        controlador = Controlador
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recepta)
        titol_recepta.text = controlador.getReceptaActiva()?.title
        autor.text = controlador.getReceptaActiva()?.autor
        passos.text = controlador.getReceptaActiva()?.descripcio
        t_prep.text = controlador.getReceptaActiva()?.tempsPrep
        t_cuina.text = controlador.getReceptaActiva()?.tempsCuina
        comensales.text = controlador.getReceptaActiva()?.numPersones
        this.ingredients = controlador.getReceptaActiva()?.ingredients!!
        if (controlador.getReceptaActiva()?.icona.equals("0")) {
            iconRecepta.setImageResource(R.drawable.cara)
        }
        if (controlador.getReceptaActiva()?.equals("1")!!) {
            iconRecepta.setImageResource(R.drawable.ou)
        }
        if (controlador.getReceptaActiva()?.equals("2")!!) {
            iconRecepta.setImageResource(R.drawable.carn)
        }
    }

    fun llista(view: View) {
        val dialog = AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_ingredients, null)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        val mAlertDialog = dialog.show()
        val customDialog = dialog.create()
        dialogView.textAfegirNousIngredients.visibility = View.INVISIBLE
        dialogView.floatingNousIngredients.visibility = View.INVISIBLE
        actualitzarLlistaIngredients(dialogView)
        dialogView.butAfegirIngredients.setOnClickListener {
            mAlertDialog.dismiss()
            intent = Intent(this, LlistaCompra::class.java)
            startActivity(intent)


        }
    }

    fun calendari(view: View) {
        controlador.setReceptaFromProposta(titol_recepta.text.toString())
        controlador.setReceptaActiva(controlador.getReceptaByName(titol_recepta.text.toString()))
        intent = Intent(this, CalendariSetmanal::class.java)
        startActivity(intent)
    }


    fun actualitzarLlistaIngredients(dialogView: View) {
        for (i in ingredients) {
            val btnIngredient: CheckBox = CheckBox(this)
            btnIngredient.text = i
            dialogView.layoutIngredientsBD.addView(btnIngredient)
            btnIngredient.isChecked = true
            controlador.afegirIngredientLlistaCompra(btnIngredient.text.toString())
            btnIngredient.setOnClickListener {
                if (btnIngredient.isChecked) {
                    controlador.afegirIngredientLlistaCompra(btnIngredient.text.toString())
                }
                if (!btnIngredient.isChecked) {
                    controlador.treureIngredientLlistaCompra(btnIngredient.text.toString())
                }
            }
        }


    }
}