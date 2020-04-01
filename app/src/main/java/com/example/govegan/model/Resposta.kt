package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Resposta (imatge:Int, title:String, desc:String) : Pregunta(imatge, title, desc){
    var idPrgegunta:String = ""
    var esCertificat:Boolean = false

}