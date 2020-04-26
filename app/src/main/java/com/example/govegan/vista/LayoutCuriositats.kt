package com.example.govegan.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.govegan.AdapterCuriositats
import com.example.govegan.R
import com.example.govegan.controlador.Controlador

class LayoutCuriositats: AppCompatActivity() {
    var controlador:Controlador
    init {
        controlador = Controlador
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.curiositat_individual)
        val adapter: AdapterCuriositats = AdapterCuriositats(this,controlador.getLlistaCuriositats())
        val viewPager: ViewPager = findViewById(R.id.viewCuriositats)
        viewPager.adapter = adapter
        viewPager.setPadding(130,0,130,0)

    }
}