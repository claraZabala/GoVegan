package com.example.govegan.vista

import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.text.Editable
import android.view.View
import android.widget.Button
import android.widget.CheckBox
import android.widget.LinearLayout
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.afegir_menu.*
import kotlinx.android.synthetic.main.afegir_menu.floatingAfegirIngredients
import kotlinx.android.synthetic.main.dialog_ingredients.*
import kotlinx.android.synthetic.main.dialog_ingredients.view.*
import java.lang.StringBuilder

class AfegirMenu : AppCompatActivity() {
    var llistaIngredients:ArrayList<String> = ArrayList()
    var controlador:Controlador = Controlador
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afegir_menu)
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


    fun calendari(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }

    fun recepta(view: View){
        intent = Intent(this, Recepta::class.java)
        startActivity(intent)
    }
}