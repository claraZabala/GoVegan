package com.example.govegan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)
    }

    fun accio1(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }
}
