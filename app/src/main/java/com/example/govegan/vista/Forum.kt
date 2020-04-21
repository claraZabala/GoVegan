package com.example.govegan.vista

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Pregunta
import com.example.govegan.model.Resposta
import com.example.govegan.model.Usuari

class Forum : AppCompatActivity() {

    var controlador: Controlador = Controlador

        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setContentView(R.layout.forum)

            //El método createFromResource() permet crear un ArrayAdapter a partir de la matriu de strings a la carpeta res.
            //El tercer paràmetre és un recurs de diseny predeterminat que defineix la manera en que es mostra l'opción seleccionada.
            val spinner: Spinner = findViewById(R.id.spinner1)
            // Create an ArrayAdapter using the string array and a default spinner layout
            ArrayAdapter.createFromResource(
                this,
                R.array.preguntes,
                android.R.layout.simple_spinner_item
            ).also { adapter ->
                // Specify the layout to use when the list of choices appears
                adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
                // Apply the adapter to the spinner
                spinner.adapter = adapter
                spinner.setAdapter(adapter)
                spinner.onItemSelectedListener = object : OnItemSelectedListener {
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



    /*On clic de enviar resposta:
    Agafem el text definit a la scroll view

    var text:String = textVew.text() ????

    new dins de la scrollview :

            <LinearLayout
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:layout_margin="10dp"
            android:orientation="vertical">

            <LinearLayout
                android:layout_width="match_parent"
                android:layout_height="match_parent"
                android:background="@color/verdClar"
                android:layout_margin="2dp"
                android:orientation="horizontal">

                <LinearLayout
                    android:layout_width="wrap_content"
                    android:layout_height="wrap_content"
                    android:orientation="vertical">

                    <LinearLayout
                        android:layout_width="match_parent"
                        android:layout_height="match_parent"
                        android:orientation="horizontal">

                        <TextView
                            android:layout_width="65dp"
                            android:layout_height="wrap_content"
                            android:layout_margin="5dp"
                            android:text="Usuari:"></TextView>

                        <TextView
                            android:layout_width="170dp"
                            android:layout_height="wrap_content"
                            android:layout_margin="5dp"
                            android:text="On puc comprar heura?          5 respostes"></TextView>

                        <Button
                            android:layout_width="52dp"
                            android:layout_height="wrap_content"
                            android:layout_margin="5dp"
                            android:background="@color/verdClar"
                            android:text="respostes"></Button>

                    </LinearLayout>


                </LinearLayout>
            </LinearLayout>
        </LinearLayout>
     */





    }

