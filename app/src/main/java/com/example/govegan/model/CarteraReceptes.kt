package com.example.govegan.model

class CarteraReceptes {
    var propostes: ArrayList<Proposta>

    init {
        propostes = ArrayList()
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:Int): Proposta? {
        var recepta = Proposta(0, nom, tempsPrep, tempsCuina, comensals,tipusRecepta, pasos, ArrayList())
        if (getByName(nom) == null) {
            propostes.add(recepta)
            return recepta
        }
        return null
    }

    fun getByName(nom: String): Proposta? {
        var recepta: Proposta? = null
        for (item in propostes) {
            if (item.title.equals(nom)){
                recepta = item
            }
        }
        return recepta
    }
}