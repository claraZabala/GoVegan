package com.example.govegan.model

class Resposta (idUsuari:String, descripcio:String, tema:String, esCertificat:Boolean, idPregunta: String) {
    var idUsuari:String = idUsuari
    var descripcio: String = descripcio
    var tema: String = tema
    private var esCertificat:Boolean = esCertificat
    var idPregunta: String = idPregunta

    fun esCertificat(): Boolean{
        return esCertificat
    }

    constructor():this( "", "", "", false, "")
}
