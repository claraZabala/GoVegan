package com.example.govegan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun recuperar_contrasenya(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }
}