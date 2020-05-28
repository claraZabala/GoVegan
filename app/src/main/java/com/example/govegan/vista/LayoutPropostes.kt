package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import com.example.govegan.R

class LayoutPropostes : AppCompatActivity() {
    private var adapter: AdapterPropostes = AdapterPropostes(this)
    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        setContentView(R.layout.propostes_menus)

        val viewPager: ViewPager = findViewById(R.id.viewPropostes)
        viewPager.adapter = adapter
        viewPager.setPadding(130,0,130,0)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, PaginaPrincipal::class.java)
        startActivity(intent)
    }

    fun proposta(view: View){
        intent = Intent(this, AfegirProposta::class.java)
        startActivity(intent)
    }

}