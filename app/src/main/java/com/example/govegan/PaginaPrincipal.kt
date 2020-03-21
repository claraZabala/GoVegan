package com.example.govegan

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity

class PaginaPrincipal : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)
    }

    fun accio1(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }

    fun accio2(view: View) {
        intent = Intent(this, Forum::class.java)
        startActivity(intent)
    }

    fun accio3(view: View) {
        intent = Intent(this, LlistaCompra::class.java)
        startActivity(intent)
    }

    fun accio4(view: View) {
        intent = Intent(this, LayoutPropostes::class.java)
        startActivity(intent)
    }

    fun accio5(view: View) {
        intent = Intent(this, layoutCuriositats::class.java)
        startActivity(intent)
    }
}