package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.login.*

class MainActivity: AppCompatActivity() {
    //private lateinit var auth: FirebaseAuth
    var controlador: Controlador = Controlador
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.login)
        auth = FirebaseAuth.getInstance()
    }

    /*override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        //updateUI(currentUser)
    }*/

    fun recuperarContrasenya(view: View) {
        intent = Intent(this, RecuperarContra::class.java)
        startActivity(intent)
    }

    fun login(view: View) {
        val correu = correu.text.toString()
        val password = pwd.text.toString()
        if(!TextUtils.isEmpty(correu) && !TextUtils.isEmpty(password)){
            auth.signInWithEmailAndPassword(correu,password)
                .addOnCompleteListener(this){
                        task ->
                    if(task.isSuccessful){
                        intent = Intent(this, PaginaPrincipal::class.java)
                        startActivity(intent)
                        val iD:String? = auth.currentUser?.uid
                        if(iD != null) {
                            controlador.login(iD)
                        }
                    }
                    else{
                        toast("Correu o contrasenya incorrectes")
                    }
                }
        }
        else{
            toast("Correu o contrasenya sense omplir")
        }
        /*
    val login = controlador.login(nomUsuari.text.toString(), pwd.text.toString())
    if (login.equals(0)){
        intent = Intent(this, PaginaPrincipal::class.java)
        startActivity(intent)
    } else if(login.equals(1)){
        toast("Usuari o contrasenya sense omplir")
    } else if (login.equals(2)){
        toast("Usuari o contrasenya incorrectes")
    }


     */
    }

    fun signUp(view: View) {
        intent = Intent(this, SignUp::class.java)
        startActivityForResult(intent,1)
    }
}
