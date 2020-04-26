package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraReceptes
import com.example.govegan.model.Proposta

class FacadeCarteraReceptes(baseDades: BaseDades) {
    var baseDades: BaseDades
    var carteraReceptes: CarteraReceptes
    var controlador: Controlador

    init {
        carteraReceptes = CarteraReceptes()
        this.baseDades = baseDades
        controlador = Controlador
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:Int,ingredients: ArrayList<String>, autor:String): Boolean {
        val recepta = carteraReceptes.addRecepta(nom, pasos, tempsPrep, tempsCuina, comensals, tipusRecepta,autor,ingredients)
        if (recepta == null){
            return false
        }
        controlador.setReceptaActiva(recepta)
        return true
    }

    fun getReceptaByName(nom: String): Proposta? {
        return carteraReceptes.getByName(nom)
    }

    fun getAllPropostes(): ArrayList<Proposta> {
        return carteraReceptes.propostes
    }

}