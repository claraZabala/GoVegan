package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


open class Pregunta (imatge:Int, title:String, desc:String) {
    var id:String = ""
    var idUsuari:String= ""
    var descripcio:String = ""
    val respostes = arrayOf<Resposta>()
    val tema: String = ""


    fun initRespostes(){
        //Falta completar
    }

    fun crearResposta(){
        // Falta completar
    }

    fun mostrarRespostes(){
        //S'obri un threat
        // Falta completar
    }
}