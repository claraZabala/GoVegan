package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Resposta (idUsuari:String, esCertificat:Boolean,idDestinatari: String, descripcio:String, tema:String) : Pregunta(idUsuari = idUsuari, descripcio = descripcio, tema = tema){
    var idDestinatari:String = ""
    var esCertificat:Boolean = false

    init{
        this.idUsuari = idUsuari
        this.esCertificat = esCertificat
    }

}