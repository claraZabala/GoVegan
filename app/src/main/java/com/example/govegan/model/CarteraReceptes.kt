package com.example.govegan.model

class CarteraReceptes {
    var propostes: ArrayList<Proposta>

    init {
        propostes = ArrayList()
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:Int): Proposta? {
        var recepta = Proposta(0, nom, tempsPrep, tempsCuina, comensals,tipusRecepta, pasos, ArrayList())
        propostes.add(recepta)
        return recepta
    }
}