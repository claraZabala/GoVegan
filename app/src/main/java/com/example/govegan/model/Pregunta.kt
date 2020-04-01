package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Pregunta (imatge:Int, title:String, desc:String) {
    var id:String = ""
    var idUsuari:String= ""
    var descripcio:String = ""
    val respostes = arrayOf<Resposta>()
    val tema: String = ""
}