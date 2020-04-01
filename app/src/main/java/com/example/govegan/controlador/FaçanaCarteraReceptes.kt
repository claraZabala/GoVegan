package com.example.govegan.controlador

import com.example.govegan.model.CarteraReceptes

class FaçanaCarteraReceptes {

    private var carteraReceptes: CarteraReceptes
        get() {
            return this.carteraReceptes
        }
        set(value) {
            this.carteraReceptes = value;
        }

    init {
        carteraReceptes = CarteraReceptes()
    }
}