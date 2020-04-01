package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


open class Pregunta (idUsuari: String, descripcio:String, tema:String) {
    var idUsuari:String= ""
    var descripcio:String = ""
    var tema: String = ""

    // Tenim un array de respostes
    var respostes: ArrayList<Pregunta> = ArrayList()

    init{
        this.idUsuari = idUsuari
        this.descripcio = descripcio
        this.tema = tema

        //idUsuari:String, esCertificat:Boolean,idDestinatari: String, descripcio:String, tema:String
        respostes.add(Resposta("Romeo29", false, "Paquita12", "Al mercadona venen pizzas de heura", "On compro els ingredients?"))
        respostes.add(Resposta("GoVeganApp", true, "Paquita12", "Pot demanar-la a la següent pàgina web https://www.deliberry.com/lasirena o a la tenda física de La Sirena.", "On compro els ingredients?"))
        respostes.add(Resposta("MiriamRR", false, "Paquita12", "A la etiqueta de cada xampú apareix si ha estat provat en animals", "Higiene"))
        respostes.add(Resposta("Samanta143", false, "SaraAO","És vegetariana", "Restaurants"))
       }


    fun crearResposta(){
        // Falta completar
    }

    fun mostrarRespostes(){
        //S'obri un threat
        // Falta completar
    }
}