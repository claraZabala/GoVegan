package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R

class PaginaPrincipal : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)
    }

    override fun onBackPressed() {
        super.onBackPressed()
        intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }

    fun calendari(view: View) {
        intent = Intent(this, CalendariSetmanal::class.java)
        startActivity(intent)
    }

    fun forum(view: View) {
        intent = Intent(this, Forum::class.java)
        startActivity(intent)
    }

    fun llistaCompra(view: View) {
        intent = Intent(this, LlistaCompra::class.java)
        startActivity(intent)
    }

    fun propostes(view: View) {
        intent = Intent(this, LayoutPropostes::class.java)
        startActivity(intent)
    }

    fun curiositats(view: View) {
        intent = Intent(this, LayoutCuriositats::class.java)
        startActivity(intent)
    }
}