package com.example.govegan.controlador

import com.example.govegan.model.CarteraCuriositats
import com.example.govegan.model.Curiositat

class FacadeCarteraCuriositats() {
    var carteraCuriositats: CarteraCuriositats

    init {
        carteraCuriositats = CarteraCuriositats()
    }

    fun getLlistaCuriositats(): ArrayList<Curiositat> {
        return carteraCuriositats.llistaCuriositats
    }

    fun getCuriositatByTheme(tema: String): Curiositat? {
        return carteraCuriositats.getCuriositatByTheme(tema)
    }

    fun changeDescCuriositat(tema: String, descNova: String): Boolean {
        return carteraCuriositats.changeDescCuriositat(tema, descNova)
    }

    fun addCuriositat(tema: String, desc: String, imatge: Int): Boolean {
        return carteraCuriositats.addCuriositat(tema, desc, imatge)
    }

    fun removeCuriositat(index: Int) {
        return carteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema: String): Int {
        return carteraCuriositats.getIndexFromList(tema)

    }

}