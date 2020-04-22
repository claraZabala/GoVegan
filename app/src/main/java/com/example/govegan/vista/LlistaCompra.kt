package com.example.govegan.vista

import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.LinearLayout
import com.example.govegan.R
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.llista_compra.*
import kotlinx.android.synthetic.main.pagina_principal.*


class LlistaCompra : AppCompatActivity() {
    var ingredients:ArrayList<String> = ArrayList()

    init{
        ingredients.add("llentia")
        ingredients.add("maduixa")
        ingredients.add("brocoli")
        ingredients.add("xocolata")
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.llista_compra)
        afegir.setOnClickListener(){
            actualitzarLlistaCompra()
        }
}

    fun actualitzarLlistaCompra(){
        for(i in ingredients) {
            var layoutIngredient: LinearLayout = LinearLayout(this)
            layoutIngredient.orientation = LinearLayout.HORIZONTAL
            var params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                100
            )

            layoutIngredient.setLayoutParams(params)

            var checkIngredient: CheckBox = CheckBox(this)
            params = ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            checkIngredient.setBackgroundColor(resources.getColor(R.color.verdClar))
            checkIngredient.setLayoutParams(params)
            var textIngredient: TextView = TextView(this)
            params = ViewGroup.LayoutParams(
                800,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            textIngredient.setLayoutParams(params)
            textIngredient.setText(i)
            textIngredient.setTextColor(resources.getColor(R.color.negre))
            textIngredient.gravity = Gravity.CENTER

            var imatgeFoto: ImageView = ImageView(this)
            params = ViewGroup.LayoutParams(
                100,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            imatgeFoto.setImageResource(this.resources.getIdentifier(i, "drawable", this.packageName))
            imatgeFoto.setLayoutParams(params)

            layoutIngredient.addView(checkIngredient)
            layoutIngredient.addView(textIngredient)
            layoutIngredient.addView(imatgeFoto)
            layoutLlistaCompra.addView(layoutIngredient)
        }

        }


    }

