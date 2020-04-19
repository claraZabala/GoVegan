package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor

class Resposta (idUsuari:String, descripcio:String, tema:String, esCertificat:Boolean, idDestinatari: String) : Pregunta(idUsuari = idUsuari, descripcio = descripcio, tema = tema){
    var idDestinatari:String = idDestinatari
    var esCertificat:Boolean = esCertificat
}