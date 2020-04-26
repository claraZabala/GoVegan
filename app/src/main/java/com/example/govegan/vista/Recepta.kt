package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Ingredient
import kotlinx.android.synthetic.main.afegir_proposta.*
import kotlinx.android.synthetic.main.recepta.*

class Recepta : AppCompatActivity() {

    var nom:String = R.id.titol_recepta.toString()
    var tempsPreparacio: Float = R.id.t_prep.toFloat()
    var tempsCuina: Float = R.id.t_cuina.toFloat()
    var numPersones : Int = R.id.comensals.toInt()
    var descripcio : String = R.id.passos.toString()
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
        titol_recepta.text = controlador.getReceptaActiva()?.title
        //autor.text = controlador.getUsuariActiu()?.nom
        passos.text = controlador.getReceptaActiva()?.descripcio
        t_prep.text = controlador.getReceptaActiva()?.tempsPrep
        t_cuina.text = controlador.getReceptaActiva()?.tempsCuina
        comensales.text = controlador.getReceptaActiva()?.numPersones
        if (controlador.getReceptaActiva()?.icona==0){
            iconRecepta.setImageResource(R.drawable.cara)
        }
        if (controlador.getReceptaActiva()?.icona==1){
            iconRecepta.setImageResource(R.drawable.ou)
        }
        if (controlador.getReceptaActiva()?.icona==2){
            iconRecepta.setImageResource(R.drawable.carn)
        }
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