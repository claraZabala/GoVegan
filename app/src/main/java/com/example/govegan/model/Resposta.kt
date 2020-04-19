package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor

class Resposta (idUsuari:String, descripcio:String, tema:String, esCertificat:Boolean, idPregunta: String) {
    var idUsuari:String = idUsuari
    var descripcio: String = descripcio
    var tema: String = tema
    var esCertificat:Boolean = esCertificat
    var idPregunta: String = idPregunta
}
