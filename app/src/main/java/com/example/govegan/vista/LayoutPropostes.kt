package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.govegan.AdapterPropostes
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.explicacio_proposta.*
import kotlinx.android.synthetic.main.propostes_menus.*

class LayoutPropostes : AppCompatActivity() {
    var controlador: Controlador = Controlador
    var adapter: AdapterPropostes = AdapterPropostes(this)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.propostes_menus)

        var viewPager: ViewPager = findViewById(R.id.viewPropostes)
        viewPager.adapter = adapter
        viewPager.setPadding(130,0,130,0)
    }

    fun proposta(view: View){
        intent = Intent(this, AfegirProposta::class.java)
        startActivity(intent)
    }

}