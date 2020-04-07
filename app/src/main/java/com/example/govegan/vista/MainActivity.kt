package com.example.govegan.vista

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import kotlinx.android.synthetic.main.login.*

class MainActivity : AppCompatActivity() {
    var controlador: Controlador
    init {
        controlador = Controlador
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
    }

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
            Toast.makeText(applicationContext, "Usuari o contrasenya sense omplir", Toast.LENGTH_LONG).show()
        } else if (login.equals(2)){
            Toast.makeText(applicationContext, "Usuari o contrasenya incorrectes", Toast.LENGTH_LONG).show()
        }
    }

    fun signUp(view: View) {
        intent = Intent(this, SignUp::class.java)
        startActivityForResult(intent,1)
    }
}
