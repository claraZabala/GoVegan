package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
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
    var nom:String = R.id.titol_recepta.toString()
    val ingredients = arrayOf<Ingredient>()
    var imatge : String = ""
    var controlador: Controlador = Controlador
    //var tempsPreparacio: Float = R.id.t_prep.toFloat()
    //var tempsCuina: Float = R.id.t_cuina.toFloat()
    //var numPersones : Int = R.id.comensals
    //var descripcio : String = R.id.passos.toString()
    //var llistaIcones = arrayOf("camiIcona1(ex:ous)", "camiIcona2(ex:carn)", "camiIcona3(ex:caraHappy)", "camiIcona4(ex:gluten)")
    //var esCertificat = false


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recepta)
        controlador.afegirReceptaLayout(titol_recepta,autor,passos,t_prep,t_cuina,comensales,iconRecepta)
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
        dialogView.butAfegirIngredients.setOnClickListener {
            mAlertDialog.dismiss()
            intent = Intent(this, LlistaCompra::class.java)
            startActivity(intent)
        }
    }

    fun calendari(view: View){
        controlador.setReceptaFromProposta(titol_recepta.text.toString())
        controlador.setReceptaActiva(titol_recepta.text.toString())
        intent = Intent(this, CalendariSetmanal::class.java)
        startActivity(intent)
    }
}