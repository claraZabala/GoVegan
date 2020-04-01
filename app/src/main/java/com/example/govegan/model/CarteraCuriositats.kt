package com.example.govegan.model

import com.example.govegan.R

class CarteraCuriositats {
    val llistaCuriositats: ArrayList<Curiositats> = ArrayList()

    init {
        llistaCuriositats.add(Curiositats(R.drawable.gastronomia, "Gastronomia", "la gastronomia ..."))
        llistaCuriositats.add(Curiositats(R.drawable.healthy, "Salut", "la salut ..."))
        llistaCuriositats.add(Curiositats(R.drawable.moda, "Moda", "la moda ..."))
        llistaCuriositats.add(Curiositats(R.drawable.altres, "Altres", "la salut ..."))
    }


    fun getCuriositatByTheme(tema: String): Curiositats? {
        for (item in llistaCuriositats) {
            if (item.title.equals(tema)){
                return item
            }
        }
        return null
    }
    fun changeDescCuriositat(tema: String,descNova: String):Boolean{
        for (item in llistaCuriositats) {
            if (item.title.equals(tema)){
                item.desc = descNova
                return true
            }
        }
        return false
    }
    fun addCuriositat(tema:String,desc:String,imatge:Int):Boolean{
        for (item in llistaCuriositats) {
            if (item.title.equals(tema)){
                return false
            }
        }
        var curiositat:Curiositats = Curiositats(imatge,tema,desc)
        return true
    }

    fun removeCuriositat(index: Int){
        llistaCuriositats.removeAt(index)
    }


    fun getIndexFromList(tema:String):Int{
        var posicio = 0
        for (item in llistaCuriositats) {
            posicio += 1
            if (item.title.equals(tema)){
                return posicio
            }
        }
        return -1

    }





}