package com.example.govegan.model

import android.util.Log
import com.example.govegan.controlador.Controlador
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import com.google.firebase.firestore.Query
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class CarteraPreguntes() {

    // Tenim un array que és una llista de preguntes
    var preguntes: ArrayList<Pregunta> = ArrayList()

    init {
        preguntes.add(Pregunta("Paquita12", "On puc comprar heura?", "On compro els ingredients?"))
        preguntes.add(Pregunta("Rosa_", "La marca Sephora és vegana?", "Moda"))
        preguntes.add(Pregunta("Paquita12", "Quin xampú puc fer servir?", "Higiene"))
        preguntes.add(Pregunta("Florenciooo", "Quin restaurant em recomenarieu a Barcelona?", "Restaurants"))
        preguntes.add(Pregunta("Carlos_00", "Sabeu si hi ha hamburgeses veganes al lidl?", "On compro els ingredients?"))
        preguntes.add(Pregunta("SaraAO", "La Woper Rebel del Burguer King es vegetariana o vegana?", "Restaurants"))
        preguntes.add(Pregunta("Lola123", "La soja texteuriçada és rica en proteina?", "Propietats"))
        preguntes.add(Pregunta("LindaSIS", "On venen seitán?", "On compro els ingredients?"))
        preguntes.add(Pregunta("Paquita12", "De que està feta la beyound meet?", "Propietats"))
        preguntes.add(Pregunta("Carlos_00", "Sabeu algun actor que sigui vegà?", "Moda"))

    }

    fun crearPregunta(idUsuari: String, descripcio: String, tema: String){
        var preg = Pregunta(idUsuari, descripcio, tema)
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

    fun crearResp( temaa: String, descripcioo: String, esCertificat: Boolean, idUsuarii: String, idDestinatari: String ) {
        var idPreg = idUsuarii + "-" + descripcioo + "-" + temaa
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                i.crearResposta(temaa, descripcioo, esCertificat, idUsuarii, idDestinatari)
            }
        }
    }

    fun mostrarRespPerIdPregunta( temaa: String, descripcioo: String, esCertificat: Boolean, idUsuarii: String, idDestinatari: String): ArrayList<Resposta>? {
        var idPreg = idUsuarii + "-" + descripcioo + "-" + temaa
        var respostesPerIdPregunta: ArrayList<Resposta>? = ArrayList()
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                respostesPerIdPregunta = i.mostrarRespostes()
            }
        }
        return respostesPerIdPregunta
    }

}