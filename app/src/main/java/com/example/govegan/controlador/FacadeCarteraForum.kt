package com.example.govegan.controlador

import com.example.govegan.model.*

class FacadeCarteraForum(baseDades: BaseDades) {
    var carteraPreguntes: CarteraForum
    var baseDades: BaseDades

    init {
        carteraPreguntes = CarteraForum()
        this.baseDades = baseDades
    }

    fun crearPreguntaF(idUsuari: String, descripcio: String, tema: String){
        carteraPreguntes.crearPregunta(idUsuari, descripcio, tema)
    }

    fun mostrarPreguntesPerTemaF(temaP: String): ArrayList<String>?{
        return carteraPreguntes.mostrarPreguntesPerTema(temaP)
    }

    fun crearRespostaF(tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String){
        carteraPreguntes.crearResp(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun mostrarRespPerIdPregF(tema:  String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String): ArrayList<Resposta>?{
        return carteraPreguntes.mostrarRespPerIdPregunta(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun getCont(usuariId: String, descripcioo: String, temaa: String): Int{
        return carteraPreguntes.getCount(usuariId, descripcioo, temaa)
    }

}
