package com.example.govegan.vista

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.govegan.AdapterCuriositats
import com.example.govegan.R
import com.example.govegan.controlador.Controlador

class layoutCuriositats: AppCompatActivity() {
    var controlador:Controlador
    init {
        controlador = Controlador()
    }

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.curiositat_individual)
        var adapter: AdapterCuriositats = AdapterCuriositats(this,controlador.getLlistaCuriositats())
        var viewPager: ViewPager = findViewById(R.id.viewCuriositats)
        viewPager.adapter = adapter
        viewPager.setPadding(130,0,130,0)
    }
}