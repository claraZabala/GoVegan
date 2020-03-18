package com.example.govegan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Propostes : AppCompatActivity()  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.propostes_menus)
    }

    fun menu1(view: View) {
        intent = Intent(this, Recepta::class.java)
        startActivity(intent)
    }
}