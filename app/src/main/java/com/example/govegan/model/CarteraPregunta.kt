package com.example.govegan.model

class CarteraPregunta {

    // Tenim un array que és una llista de preguntes
    var preguntes: ArrayList<Pregunta> = ArrayList()


    fun crearPregunta(idUsuari: String, descripcio: String, tema: String): Pregunta{
        var preg = Pregunta(idUsuari, descripcio, tema)
        preguntes.add(preg)
        return preg
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<String>?{
        val preguntesPerTema: ArrayList<String> = ArrayList()
        for (i: Pregunta in preguntes){
            if ( i.tema.equals(temaP) ){
                preguntesPerTema.add(i.descripcio)
            }
        }
        return preguntesPerTema
    }

    fun crearResp( temaa: String, descripcioo: String, esCertificat: Boolean, idUsuarii: String, idDestinatari: String , descPreg : String) : Pregunta? {
        val idPreg = idDestinatari + "-" + descPreg + "-" + temaa
        var preg : Pregunta? = null
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                i.crearResposta(temaa, descripcioo, esCertificat, idUsuarii, idDestinatari, descPreg)
                preg = i
            }
        }
        return preg
    }

    fun mostrarRespPerIdPregunta( temaa: String, descripcioo: String, esCertificat: Boolean, idUsuarii: String, idDestinatari: String): ArrayList<Resposta>? {
        val idPreg = idUsuarii + "-" + descripcioo + "-" + temaa
        var respostesPerIdPregunta: ArrayList<Resposta>? = ArrayList()
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                respostesPerIdPregunta = i.mostrarRespostes()
            }
        }
        return respostesPerIdPregunta
    }

    fun getCount(idUsuarii:String,descripcioo: String, temaa: String): Int{
        var cont:Int = 0
        val idPreg = idUsuarii + "-" + descripcioo + "-" + temaa
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                cont = i.getContador()
            }
        }
        return cont
    }

    fun getUsari(descripcioo: String): String{
        var id: String = ""
        for (i: Pregunta in preguntes) {
            if (i.descripcio.equals(descripcioo)) {
                id = i.idUsuari
            }
        }
        return id
    }

    fun mostrarRespostesPerDesc( idUsuari: String, desc : String, tema: String): ArrayList<String>?{
        val idPreg = idUsuari + "-" + desc + "-" + tema
        var respostesDesc: ArrayList<String>? = ArrayList()
        for (i: Pregunta in preguntes) {
            if (i.idPregunta.equals(idPreg)) {
                respostesDesc =  i.mostrarRespostesPerDesc( idUsuari, desc , tema)
            }
        }
        return respostesDesc
    }

    fun getLlistaPreguntes(): ArrayList<Pregunta>? {
        return preguntes
    }


}