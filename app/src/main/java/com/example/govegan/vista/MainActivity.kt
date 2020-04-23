package com.example.govegan.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.login.*

class MainActivity: AppCompatActivity() {
    //private lateinit var auth: FirebaseAuth
    var controlador: Controlador = Controlador

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        //auth = FirebaseAuth.getInstance()
    }

    /*override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }*/

    fun recuperar_contrasenya(view: View) {
        intent = Intent(this, RecuperarContra::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        var login = controlador.login(nomUsuari.text.toString(), pwd.text.toString())
        if (login.equals(0)){
            intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        } else if(login.equals(1)){
            toast("Usuari o contrasenya sense omplir")
        } else if (login.equals(2)){
            toast("Usuari o contrasenya incorrectes")
        }
    }

    fun signUp(view: View) {
        intent = Intent(this, SignUp::class.java)
        startActivityForResult(intent,1)
    }
}
