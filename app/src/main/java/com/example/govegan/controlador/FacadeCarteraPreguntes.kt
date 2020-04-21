package com.example.govegan.controlador

import com.example.govegan.model.*

class FacadeCarteraPreguntes() {
    var carteraPreguntes: CarteraPreguntes

    init {
        carteraPreguntes = CarteraPreguntes()
    }

    fun crearPreguntaF(idUsuari: String, descripcio: String, tema: String){
        carteraPreguntes.crearPregunta(idUsuari, descripcio, tema)
    }

    fun mostrarPreguntesPerTemaF(temaP: String): ArrayList<Pregunta>?{
        return carteraPreguntes.mostrarPreguntesPerTema(temaP)
    }

    fun crearRespostaF(tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String){
        carteraPreguntes.crearResp(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun mostrarRespPerIdPregF(tema:  String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String): ArrayList<Resposta>?{
        return carteraPreguntes.mostrarRespPerIdPregunta(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

}
