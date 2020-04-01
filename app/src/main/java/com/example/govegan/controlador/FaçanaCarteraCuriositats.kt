package com.example.govegan.controlador

import com.example.govegan.model.CarteraCuriositats
import com.example.govegan.model.Curiositats

class FaçanaCarteraCuriositats() {
    var carteraCuriositats: CarteraCuriositats
    init{
        carteraCuriositats = CarteraCuriositats()
    }
    fun getCuriositatByTheme(tema: String): Curiositats? {
       return carteraCuriositats.getCuriositatByTheme(tema)
    }
    fun changeDescCuriositat(tema: String,descNova: String):Boolean{
        return carteraCuriositats.changeDescCuriositat(tema,descNova)
    }
    fun addCuriositat(tema:String,desc:String,imatge:Int):Boolean{
        return carteraCuriositats.addCuriositat(tema,desc,imatge)
    }

    fun removeCuriositat(index: Int){
        return carteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema:String):Int{
        return carteraCuriositats.getIndexFromList(tema)

    }






}