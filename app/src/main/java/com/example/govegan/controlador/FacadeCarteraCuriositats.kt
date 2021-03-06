package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraCuriositats
import com.example.govegan.model.Curiositat

class FacadeCarteraCuriositats(baseDades: BaseDades) {
    var carteraCuriositats: CarteraCuriositats = CarteraCuriositats()
    var baseDades: BaseDades

    init {
        this.baseDades = baseDades
    }

    fun getImatge(position: Int):String?{
       return carteraCuriositats.llistaCuriositats[position].imatge
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

    fun addCuriositat(tema: String, desc: String, imatge: String?): Curiositat? {
        return carteraCuriositats.addCuriositat(tema, desc, imatge)
    }

    fun removeCuriositat(index: Int) {
        return carteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema: String): Int {
        return carteraCuriositats.getIndexFromList(tema)

    }

    fun getNumCuriositats(): Int {
        return carteraCuriositats.llistaCuriositats.size
    }

    fun getTitle(position: Int): String {
        return carteraCuriositats.getTitle(position)
    }

    fun getDescripcio(position: Int): String {
        return carteraCuriositats.getDescripcio(position)
    }

    fun getLlistaBaseDades() {
        carteraCuriositats.llistaCuriositats = baseDades.getAllCuriositats()
    }

}
