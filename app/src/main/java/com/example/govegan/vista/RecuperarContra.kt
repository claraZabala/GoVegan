package com.example.govegan.vista

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.recuperar_contra.*

class RecuperarContra: AppCompatActivity() {
    val controlador = Controlador
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recuperar_contra)
    }

    fun recuperarContra(view: View){
        var success = false
        FirebaseAuth.getInstance().sendPasswordResetEmail(correu.text.toString()).addOnCompleteListener{ task ->
            if(task.isSuccessful){
                success = true
                toast("CORREU ENVIAT")}
            else{
                toast("CORREU ERRONI")}
        }
    }
}