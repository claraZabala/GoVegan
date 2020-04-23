package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraReceptes

class FacadeCarteraReceptes(baseDades: BaseDades) {
    var baseDades: BaseDades
    var carteraReceptes: CarteraReceptes


    init {
        carteraReceptes = CarteraReceptes()
        this.baseDades = baseDades
    }
}