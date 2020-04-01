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


    //Creem un diccionari de clau-valor fins a que puguem treballar amb la base de dades
    // La clau serà la descripció de la pregunta i el valor l'id de la pregunta
    //En aquest trobarem 3 exemples de preguntes
    var myMap: Map<String, Int> = mutableMapOf("On puc comprar Heura?" to 1, "La marca Sephora és vegana?" to 2, "Quins plats són recomanables per aconseguir massa muscular?" to 3)

    fun afegirPregunta(){
        
    }

}