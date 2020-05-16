package com.example.govegan.controlador

import com.example.govegan.model.*

class FacadeCarteraPreguntes(baseDades: BaseDades) {
    var carteraPreguntes: CarteraPregunta = CarteraPregunta()
    var baseDades: BaseDades

    init {
        this.baseDades = baseDades
    }

    fun crearPreguntaF(idUsuari: String, descripcio: String, tema: String): Pregunta{
        return carteraPreguntes.crearPregunta(idUsuari, descripcio, tema)
    }

    fun mostrarPreguntesPerTemaF(temaP: String): ArrayList<String>?{
        return carteraPreguntes.mostrarPreguntesPerTema(temaP)
    }

    fun crearRespostaF(tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String, descPreg : String): Pregunta? {
        return carteraPreguntes.crearResp(tema, descripcio, esCertificat, idUsuari, idDestinatari, descPreg )
    }

    fun mostrarRespPerIdPregF(tema:  String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String): ArrayList<Resposta>?{
        return carteraPreguntes.mostrarRespPerIdPregunta(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun getCont(usuariId: String, descripcioo: String, temaa: String): Int{
        return carteraPreguntes.getCount(usuariId, descripcioo, temaa)
    }

    fun getUsari(descripcioo: String): String{
        return carteraPreguntes.getUsari(descripcioo)
    }

    fun mostrarRespostesPerDesc( idUsuari: String, desc : String, tema: String): ArrayList<String>?{
        return carteraPreguntes.mostrarRespostesPerDesc( idUsuari, desc , tema)
    }

    fun getLlistaPreguntes(): ArrayList<Pregunta>? {
        return carteraPreguntes.getLlistaPreguntes()
    }

    fun getLListaBaseDades() {
        carteraPreguntes.preguntes = baseDades.getAllPreguntes()
    }

}
