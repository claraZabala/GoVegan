package com.example.govegan.vista

import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginLeft
import androidx.core.view.marginRight
import androidx.core.view.setPadding
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.dialog_ingredients.view.*
import kotlinx.android.synthetic.main.llista_compra.*

class LlistaCompra : AppCompatActivity() {
    var ingredients:ArrayList<String> = ArrayList()
    var llistaIngredients:ArrayList<String> = ArrayList()
    var controlador: Controlador = Controlador
    var removeIngredient:ArrayList<String> = ArrayList()

    init{
        ingredients  = controlador.getLlistaIngredientsUsuari()!!
        llistaIngredients = controlador.getAllIngredientsByName()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.llista_compra)
        actualitzarLlistaCompra()
        eliminar.setOnClickListener {
            removeIngredients()
            actualitzarLlistaCompra()
        }
        afegir.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_ingredients, null)
            dialog.setView(dialogView)
            dialog.setCancelable(true)
            val mAlertDialog = dialog.show()
            val customDialog = dialog.create()
            actualitzarLlistaIngredients(dialogView)
            dialogView.butAfegirIngredients.setOnClickListener{
                mAlertDialog.dismiss()
                actualitzarLlistaCompra()
            }
            dialogView.floatingNousIngredients.setOnClickListener{
                afegirNouIngredientLlista(dialogView)

            }
            dialogView.floatingLupa.setOnClickListener{
                buscarIngredient(dialogView)

            }
        }

}


     fun actualitzarLlistaIngredients(dialogView: View) {
        dialogView.layoutIngredientsBD.removeAllViews()
        for (i in llistaIngredients) {
            if (i !in ingredients) {
                val btnIngredient: CheckBox = CheckBox(this)
                btnIngredient.text = i
                dialogView.layoutIngredientsBD.addView(btnIngredient)
                btnIngredient.isChecked = false
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
     fun removeIngredients(){
        for(i in removeIngredient){
            ingredients.remove(i)
            controlador.treureIngredientLlistaCompra(i)
        }
        removeIngredient.clear()
    }
     fun actualitzarLlistaCompra(){
        ingredients  = controlador.getLlistaIngredientsUsuari()!!
        layoutLlistaCompra.removeAllViews()
        for(i in ingredients) {
            val layoutIngredient: LinearLayout = LinearLayout(this)
            layoutIngredient.orientation = LinearLayout.HORIZONTAL
            var params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                100
            )
            layoutIngredient.layoutParams = params
            layoutIngredient.weightSum = 7f

            val checkIngredient: CheckBox = CheckBox(this)
            params = LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,1f
            )
            checkIngredient.setBackgroundColor(resources.getColor(R.color.verdClar))
            checkIngredient.layoutParams = params
            checkIngredient.setOnClickListener {
                if(checkIngredient.isChecked){
                    removeIngredient.add(i)
                }
                if(!checkIngredient.isChecked){
                    ingredients.add(i)
                }

            }
            val textIngredient: TextView = TextView(this)
            params = LinearLayout.LayoutParams(0,
                ViewGroup.LayoutParams.WRAP_CONTENT,5f
            )
            textIngredient.layoutParams = params
            textIngredient.text = i
            textIngredient.setTextColor(resources.getColor(R.color.negre))
            textIngredient.gravity = Gravity.CENTER

            val imatgeFoto: ImageView = ImageView(this)
            params = ViewGroup.LayoutParams(
                75,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            controlador.carregarImatgeIngredient(this,imatgeFoto,i)
            imatgeFoto.layoutParams = params
            layoutIngredient.addView(checkIngredient)
            layoutIngredient.addView(textIngredient)
            layoutIngredient.addView(imatgeFoto)
            layoutLlistaCompra.addView(layoutIngredient)
        }

        }


    fun buscarIngredient(dialogView: View) {
        if (dialogView.textCercaIngredient.text.toString().isEmpty()) {
            actualitzarLlistaIngredients(dialogView)
        }

        else {
            for (i in llistaIngredients) {
                if (i.equals(dialogView.textCercaIngredient.text.toString())) {
                    if(i !in ingredients) {
                        dialogView.layoutIngredientsBD.removeAllViews()
                        val btnIngredient: CheckBox = CheckBox(this)
                        btnIngredient.text = i
                        dialogView.layoutIngredientsBD.addView(btnIngredient)
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
        }
        dialogView.textCercaIngredient.setText("")
    }


     fun afegirNouIngredientLlista(dialogView: View){
         val ingredient = dialogView.textAfegirNousIngredients.text.toString()
        if (!ingredient.equals("")){
            val chkBoxNouIngredient = CheckBox(this)
            chkBoxNouIngredient.text = ingredient
            if(ingredient !in llistaIngredients) {
                dialogView.layoutIngredientsBD.addView(chkBoxNouIngredient, 0)
                chkBoxNouIngredient.isChecked = true
                ingredients.add(ingredient)
                chkBoxNouIngredient.setOnClickListener {
                    if (chkBoxNouIngredient.isChecked) {
                        controlador.afegirIngredientLlistaCompra(chkBoxNouIngredient.text.toString())
                    }
                    if (!chkBoxNouIngredient.isChecked) {
                        controlador.treureIngredientLlistaCompra(chkBoxNouIngredient.text.toString())
                    }
                }
            }
        }
        dialogView.textAfegirNousIngredients.setText("")
    }
}




