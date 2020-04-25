package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraReceptes

class FacadeCarteraReceptes(baseDades: BaseDades) {
    var baseDades: BaseDades
    var carteraReceptes: CarteraReceptes
    var controlador: Controlador

    init {
        carteraReceptes = CarteraReceptes()
        this.baseDades = baseDades
        controlador = Controlador
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:Int) {
        controlador.setReceptaActiva(carteraReceptes.addRecepta(nom, pasos, tempsPrep, tempsCuina, comensals,tipusRecepta))
    }

}