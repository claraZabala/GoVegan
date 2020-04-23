package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Ingredient
import kotlinx.android.synthetic.main.recepta.*

class Recepta : AppCompatActivity() {

    var nom:String = R.id.titol_recepta.toString()
    var tempsPreparacio:Float = 0.0F
    var numPersones : Int = 0
    var descripcio : String = ""
    val ingredients = arrayOf<Ingredient>()
    var imatge : String = ""
    var llistaIcones = arrayOf("camiIcona1(ex:ous)", "camiIcona2(ex:carn)", "camiIcona3(ex:caraHappy)", "camiIcona4(ex:gluten)")
    var esCertificat = false
    var controlador: Controlador

    init {
        controlador = Controlador
    }



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recepta)
    }

    fun llista(view: View) {
        intent = Intent(this, LlistaCompra::class.java)
        startActivity(intent)
    }

    fun calendari(view: View){
        controlador.setReceptaFromProposta(titol_recepta.text.toString())
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }
}