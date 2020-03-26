package com.example.govegan.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.govegan.R

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

    fun recuperar_contrasenya(view: View) {
        intent = Intent(this, RecuperarContra::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        intent = Intent(this, PaginaPrincipal::class.java)
        startActivity(intent)
    }

    fun signUp(view: View) {
        intent = Intent(this, SignUp::class.java)
        startActivity(intent)
    }
}
