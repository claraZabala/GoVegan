package com.example.govegan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Calendari_Setmanal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.calendari_setmanal)
    }

    fun afegirMenu(view: View){
        intent = Intent(this, AfegirMenu::class.java)
        startActivity(intent)
    }
}
