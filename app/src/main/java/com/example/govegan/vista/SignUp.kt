package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.signup.*

class SignUp: AppCompatActivity() {
    var controlador = Controlador()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
    }

    fun paginaPrincipal(view: View) {
        var registre = controlador.registre(nom.toString(), cognoms.toString(), nomUsuari.toString(), mail.toString(),
            pwd.toString(), pwd2.toString(), edat.selectedItem.toString())
        if (registre.equals(0)){
            intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        } else if (registre.equals(1)){
            println("Has d'omplir tots els camps")
        } else if (registre.equals(2)){
            println("Les contrasenyes han de coincidir")
        } else {
            println("El nom d'usuari ja existeix")
        }
    }
}