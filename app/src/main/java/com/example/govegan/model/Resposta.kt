package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor

class Resposta (idUsuari:String, descripcio:String, tema:String, esCertificat:Boolean, idDestinatari: String) : Pregunta(idUsuari = idUsuari, descripcio = descripcio, tema = tema){
    var idDestinatari:String = idDestinatari
        get(){
            return field
        }
        set(newId){
            field = newId
        }
    var esCertificat:Boolean = esCertificat
        get(){
            return field
        }
        set(newEsCertificat){
            field = newEsCertificat
        }
}