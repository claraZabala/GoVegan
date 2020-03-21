package com.example.govegan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.pagina_principal.*

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
