package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.CheckBox
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.afegir_proposta.*
import kotlinx.android.synthetic.main.afegir_proposta.floatingAfegirIngredients
import kotlinx.android.synthetic.main.dialog_ingredients.view.*

class AfegirProposta : AppCompatActivity() {
    var llistaIngredients:ArrayList<String> = ArrayList()
    var controlador:Controlador = Controlador
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afegir_proposta)
        llistaIngredients = controlador.getAllIngredientsByName()



        floatingAfegirIngredients.setOnClickListener() {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_ingredients, null)
            dialog.setView(dialogView)
            dialog.setCancelable(false)
            val mAlertDialog = dialog.show()
            val customDialog = dialog.create()
            actualitzarLlistaIngredients(dialogView)


            dialogView.butAfegirIngredients.setOnClickListener{
                mAlertDialog.dismiss()
            }
            dialogView.floatingNousIngredients.setOnClickListener{
                AfegirNouIngredientLlista(dialogView)

            }
            dialogView.floatingLupa.setOnClickListener{
                buscarIngredient(dialogView)

            }


        }

    }

    fun buscarIngredient(dialogView: View) {
        if (dialogView.textCercaIngredient.text.toString().length == 0) {
            actualitzarLlistaIngredients(dialogView)
        }

        else {
            for (i in llistaIngredients) {
                if (i.equals(dialogView.textCercaIngredient.text.toString())) {
                    dialogView.layoutIngredientsBD.removeAllViews()
                    var btnIngredient: CheckBox = CheckBox(this)
                    btnIngredient.setText(i)
                    dialogView.layoutIngredientsBD.addView(btnIngredient)
                }
            }
        }
        dialogView.textCercaIngredient.setText("")
    }

    fun actualitzarLlistaIngredients(dialogView: View){
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
        }

    }

    fun AfegirNouIngredientLlista(dialogView: View){
        if (!dialogView.textAfegirNousIngredients.toString().equals("")){
            var chkBoxNouIngredient: CheckBox = CheckBox(this)
            chkBoxNouIngredient.setText(dialogView.textAfegirNousIngredients.text.toString())
            if(dialogView.textAfegirNousIngredients.text.toString() !in llistaIngredients) {
                dialogView.layoutIngredientsBD.addView(chkBoxNouIngredient)
                llistaIngredients.add(chkBoxNouIngredient.text.toString())
                llistaIngredientsCompra.add(chkBoxNouIngredient.text.toString())
                textIngredients.text = llistaIngredientsCompra.toString()
            }
        }
        dialogView.textAfegirNousIngredients.setText("")
    }

    fun recepta(view: View){
        var nom: String = nomPlat.text.toString()
        var pasos: String = pasos.text.toString()
        var tempsPrep: String = temps_prep.text.toString()
        var tempsCuina: String = temps_cuina.text.toString()
        var comensals: String = comensals.text.toString()
        var tipusRecepta: Int = 4
        if (teCarn.isActivated){
            tipusRecepta = 2
        }
        if (teDerivats.isActivated){
            tipusRecepta = 1
        }
        if (!teDerivats.isActivated and !teCarn.isActivated){
            tipusRecepta = 0
        }
        var result = controlador.afegirReceptaNova(nom,pasos,tempsPrep,tempsCuina,comensals,tipusRecepta)
        if (result==1){
            toast("Has d'omplir tots els camps")
        } else if (result==0) {
            intent = Intent(this, Recepta::class.java)
            startActivity(intent)
        }
    }
}