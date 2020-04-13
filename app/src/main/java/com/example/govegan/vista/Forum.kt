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

    var controlador:Controlador = Controlador

    // Tenim un array de preguntes
    var preguntes: ArrayList<Pregunta> = ArrayList()

    init {
        preguntes.add(Pregunta("Paquita12", "On puc comprar heura?", "On compro els ingredients?", "Paquita12-On puc comprar heura?-On compro els ingredients?"))
        preguntes.add(Pregunta("Rosa_", "La marca Sephora és vegana?", "Moda", "Rosa_-La marca Sephora és vegana?-Moda"))
        preguntes.add(Pregunta("Paquita12", "Quin xampú puc fer servir?", "Higiene", "Paquita12-Quin xampú puc fer servir?-Higiene"))
        preguntes.add(Pregunta("Florenciooo", "Quin restaurant em recomenarieu a Barcelona?", "Restaurants", "Florenciooo-Quin restaurant em recomenarieu a Barcelona?-Restaurants"))
        preguntes.add(Pregunta("Carlos_00", "Sabeu si hi ha hamburgeses veganes al lidl?", "On compro els ingredients?", "Carlos_00-Sabeu si hi ha hamburgeses veganes al lidl?-On compro els ingredients?"))
        preguntes.add(Pregunta("SaraAO", "La Woper Rebel del Burguer King es vegetariana o vegana?", "Restaurants", "SaraAO-La Woper Rebel del Burguer King es vegetariana o vegana?-Restaurants"))
        preguntes.add(Pregunta("Lola123", "La soja texteuriçada és rica en proteina?", "Propietats", "Lola123-La soja texteuriçada és rica en proteina?-Propietats"))
        preguntes.add(Pregunta("LindaSIS", "On venen seitán?", "On compro els ingredients?", "LindaSIS-On venen seitán?-On compro els ingredients?"))
        preguntes.add(Pregunta("Paquita12", "De que està feta la beyound meet?", "Propietats", "Paquita12-De que està feta la beyound meet?-Propietats"))
        preguntes.add(Pregunta("Carlos_00", "Sabeu algun actor que sigui vegà?", "Moda", "Carlos_00-Sabeu algun actor que sigui vegà?-Moda"))

    }

    fun crearPregunta(idUsuari: String, descripcio: String, tema: String, idPregunta: String){
        //El constructor de pregunta rep per paràmetre:
        //idUsuari: String, descripcio:String, tema:String
        var preg = Pregunta(idUsuari, descripcio, tema, idPregunta)
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<Pregunta>?{
        var preguntesPerTema: ArrayList<Pregunta> = ArrayList()
        for (i: Pregunta in preguntesPerTema){
            if ( i.tema.equals(temaP) ){
                preguntesPerTema.add(i);
            }
        }
        return preguntesPerTema
    }

    fun crearResp( tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String ){
        //tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String
        //No se com fer-ho per cridar als mètodes de pregunta: crear una pregunta a forum per cridar als mètodes té sentit?
        // També per mostrarRespostesPerDestinatari
        //I finalment cridar a aquests 2 mètodes des de controlador
}

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

}
