package com.example.govegan.controlador

import com.example.govegan.model.Curiositats

class Controlador {
    var façanaCarteraCuriositats: FaçanaCarteraCuriositats
    var façanaCarteraReceptes: FaçanaCarteraReceptes

    init{
        façanaCarteraCuriositats = FaçanaCarteraCuriositats()
        façanaCarteraReceptes = FaçanaCarteraReceptes()
    }
    fun getLlistaCuriositats():ArrayList<Curiositats> {
    return façanaCarteraCuriositats.getLlistaCuriositats()

    }

    fun getCuriositatByTheme(tema: String): Curiositats? {
        return façanaCarteraCuriositats.getCuriositatByTheme(tema)
    }
    fun changeDescCuriositat(tema: String,descNova: String):Boolean{
        return façanaCarteraCuriositats.changeDescCuriositat(tema,descNova)
    }
    fun addCuriositat(tema:String,desc:String,imatge:Int):Boolean{
        return façanaCarteraCuriositats.addCuriositat(tema,desc,imatge)
    }

    fun removeCuriositat(index: Int){
        return façanaCarteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema:String):Int{
        return façanaCarteraCuriositats.getIndexFromList(tema)

    }

}