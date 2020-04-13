package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Resposta (idUsuari:String, esCertificat:Boolean,idDestinatari: String, descripcio:String, tema:String) : Pregunta(idUsuari = idUsuari, descripcio = descripcio, tema = tema){
    internal var idDestinatari:String
        get(){
            return idDestinatari
        }
        set(newId){
            this.idDestinatari = newId
        }
    private var esCertificat:Boolean
        get(){
            return esCertificat
        }
        set(newEsCertificat){
            this.esCertificat = newEsCertificat
        }


    init{
        this.esCertificat = esCertificat
        this.idDestinatari = idDestinatari
    }

}