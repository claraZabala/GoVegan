package com.example.govegan.model

class Pregunta (idUsuari: String, descripcio:String, tema:String) {
    var idPregunta: String = "$idUsuari-$descripcio-$tema"
    private var contRespostes: Int = 0

    // Tenim un array de respostes
    private var respostes: ArrayList<Resposta> = ArrayList()
    var idUsuari: String = idUsuari
    var descripcio: String = descripcio
    var tema: String = tema


    init {
        respostes.add(
            Resposta(
                "Romeo29",
                "Al mercadona venen pizzas de heura",
                "On compro els ingredients?",
                false,
                "Paquita12-On puc comprar heura?-On compro els ingredients?"
            )
        )
        respostes.add(
            Resposta(
                "GoVeganApp",
                "Pot demanar-la a la següent pàgina web https://www.deliberry.com/lasirena o a la tenda física de La Sirena.",
                "On compro els ingredients?",
                true,
                "Paquita12-On puc comprar heura?-On compro els ingredients?"
            )
        )
        respostes.add(
            Resposta(
                "MiriamRR",
                "A la etiqueta de cada xampú apareix si ha estat provat en animals",
                "Higiene",
                false,
                "Paquita12-Quin xampú puc fer servir?-Higiene"
            )
        )
        respostes.add(
            Resposta(
                "Samanta143",
                "És vegetariana",
                "Restaurants",
                false,
                "SaraAO-La Woper Rebel del Burguer King es vegetariana o vegana?-Restaurants"
            )
        )
    }

    fun crearResposta (
        tema: String,
        descripcio: String,
        esCertificat: Boolean,
        idUsuari: String,
        idDestinatari: String,
        descPreg : String
    ) : Resposta{
        val idP = "$idDestinatari-$descPreg-$tema"
        val resp = Resposta(idUsuari, descripcio, tema, esCertificat, idP)
        respostes.add(resp)
        contRespostes++
        return resp
    }

    fun mostrarRespostes(): ArrayList<Resposta>? {
        return this.respostes
    }

    fun getContador(): Int {
        return contRespostes
    }

    fun getUsari(): String {
        return idUsuari
    }


    fun mostrarRespostesPerDesc( idUsuari: String, desc : String, tema: String): ArrayList<String>?{
        val idPreg: String = "$idUsuari-$desc-$tema"
        val respostesDesc: ArrayList<String>? = ArrayList()
        for (i: Resposta in respostes) {
            if (i.idPregunta.equals(idPreg)) {
                val resp : String = (i.idUsuari + ": " + i.descripcio)
                respostesDesc?.add(resp)
            }
        }
        return respostesDesc
    }

    constructor():this( "", "", "")

}

