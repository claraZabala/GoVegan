package com.example.govegan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class SignUp: AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
    }

    fun paginaPrincipal(view: View) {
        intent = Intent(this, PaginaPrincipal::class.java)
        startActivity(intent)
    }
}