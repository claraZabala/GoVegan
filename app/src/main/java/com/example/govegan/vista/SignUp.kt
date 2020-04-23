package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.signup.*

class SignUp: AppCompatActivity() {
    var controlador: Controlador
    private lateinit var dbReference:DatabaseReference
    private lateinit var auth:FirebaseAuth
    init {
        controlador = Controlador
        dbReference = FirebaseDatabase.getInstance().reference.child("USER")
        auth=FirebaseAuth.getInstance()
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.signup)
        //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
        //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
        val spinner: Spinner = findViewById(R.id.edat)
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter.createFromResource(
            this,
            R.array.edat,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            // Specify the layout to use when the list of choices appears
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            // Apply the adapter to the spinner
            spinner.adapter = adapter
            spinner.setAdapter(adapter)
            spinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onItemSelected(
                    adapterView: AdapterView<*>,
                    view: View?,
                    pos: Int,
                    id: Long
                ) {
                    Toast.makeText(
                        adapterView.context,
                        adapterView.getItemAtPosition(pos) as String, Toast.LENGTH_SHORT
                    ).show()
                }

                override fun onNothingSelected(parent: AdapterView<*>?) {}
            }

        }
    }

    fun paginaPrincipal(view: View) {
        var registre = controlador.registre(nom.text.toString(), cognoms.text.toString(), nomUsuari.text.toString(), mail.text.toString(),
            pwd.text.toString(), pwd2.text.toString(), edat.selectedItem.toString())
        if (registre.equals(0)){
            auth.createUserWithEmailAndPassword(mail.text.toString(),pwd.text.toString())
                .addOnCompleteListener(this){
                        val user: FirebaseUser? = auth.currentUser
                        val userBD = dbReference.child(nomUsuari.text.toString())

                        userBD.child("Name").setValue(nom.text.toString())
                        userBD.child("Cognoms").setValue(cognoms.text.toString())
                    }
            intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        } else if (registre.equals(1)){
            toast("Has d'omplir tots els camps")
        } else if (registre.equals(2)){
            toast("Les contrasenyes han de coincidir")
        } else {
            toast("El nom d'usuari ja existeix")
        }
    }
}