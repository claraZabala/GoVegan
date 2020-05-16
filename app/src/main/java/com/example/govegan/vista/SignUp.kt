package com.example.govegan.vista

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import kotlinx.android.synthetic.main.signup.*
import java.time.LocalDate
import java.time.temporal.TemporalField
import java.time.temporal.WeekFields
import java.util.*

class SignUp: AppCompatActivity() {
    var controlador: Controlador = Controlador
    private lateinit var dbReference:DatabaseReference
    private lateinit var auth:FirebaseAuth


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
            spinner.adapter = adapter
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

    @RequiresApi(Build.VERSION_CODES.O)
    fun paginaPrincipal(view: View) {
        if (mail.text.toString().isBlank() or pwd.text.toString().isBlank()){
            toast("Has d'omplir tots els camps")
        }
        else {
            auth = FirebaseAuth.getInstance()
            auth.createUserWithEmailAndPassword(mail.text.toString(), pwd.text.toString())
                .addOnCompleteListener(this) { task ->

                    if (task.isSuccessful) {
                        val userID = auth.currentUser?.uid
                        val date: LocalDate = LocalDate.now()
                        val woy: TemporalField =
                            WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear()
                        val weekNumber: Int =
                            date.get(woy) //Aquesta variable et diu el nombre de setmana de l'any
                        val registre = controlador.registre(
                            userID,
                            nom.text.toString(),
                            cognoms.text.toString(),
                            correu.text.toString(),
                            mail.text.toString(),
                            pwd.text.toString(),
                            pwd2.text.toString(),
                            edat.selectedItem.toString(),
                            weekNumber
                        )
                        if (registre.equals(0)) {
                            intent = Intent(this, MainActivity::class.java)
                            startActivity(intent)
                        } else if (registre.equals(1)) {
                            toast("Has d'omplir tots els camps")
                            auth.currentUser?.delete()
                        } else if (registre.equals(2)) {
                            toast("Les contrasenyes han de coincidir")
                            auth.currentUser?.delete()
                        } else if (registre.equals(4)) {
                            toast("Correu no valid")
                            auth.currentUser?.delete()
                        } else if (registre.equals(3)) {
                            toast("El nom d'usuari ja existeix")
                            auth.currentUser?.delete()
                        } else if (registre.equals(5)) {
                            toast("Contrasenya molt curta")
                            auth.currentUser?.delete()
                        }
                    } else {
                        //TODO: aixo ja s'ha comprovat abans, no?
                        if (pwd.toString().length < 6) {
                            toast("Contrasenya molt curta")
                        } else {
                            toast("Correu repetit")
                        }
                    }
                }
        }
    }
}