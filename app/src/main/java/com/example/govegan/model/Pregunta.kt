package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


open class Pregunta (idUsuari: String, descripcio:String, tema:String, idPregunta: String) {
    internal var idPregunta : String
        get(){
            return idPregunta
        }
        set(id){
            this.idPregunta = id
        }
    private var contRespostes: Int
        get(){
            return contRespostes
        }
        set(cont){
            this.contRespostes = cont
        }
    private var idUsuari:String
        get(){
            return idUsuari
        }
        set(newIdUsuari){
            this.idUsuari = newIdUsuari
        }
    private var descripcio:String
        get(){
            return descripcio
        }
        set(descripcio){
            this.descripcio = descripcio
        }
    internal var tema: String
        get(){
            return tema
        }
        set(tema){
            this.tema = tema
        }

    // Tenim un array de respostes
    var respostes: ArrayList<Resposta> = ArrayList()

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


    fun crearResposta( tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String ){
        // El constructor de resposta rep per paràmetre:
        // idUsuari:String, esCertificat:Boolean,idDestinatari: String, descripcio:String, tema:String
        var resp = Resposta(idUsuari, esCertificat,idDestinatari, descripcio, tema)
        contRespostes++
    }

    fun mostrarRespostesPerIdPregunta(idPregunta: String):  ArrayList<Resposta>?{
        var respostesPerDestinatari: ArrayList<Resposta> = ArrayList()
        for (i: Resposta in respostes){
            if ( i.idPregunta.equals(idPregunta) ){
                respostesPerDestinatari.add(i);
            }
        }
        return respostesPerDestinatari
    }
}