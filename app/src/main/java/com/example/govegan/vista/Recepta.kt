package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R

class Recepta : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recepta)
    }

    fun llista(view: View) {
        intent = Intent(this, LlistaCompra::class.java)
        startActivity(intent)
    }

    fun calendari(view: View){
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }
}