package com.example.govegan.model

import com.example.govegan.R

class CarteraCuriositats {
    var llistaCuriositats: ArrayList<Curiositat> = ArrayList()
        get(){
            return field
        }
        set(newCuriositat){
            field = newCuriositat
        }

    init {
        llistaCuriositats.add(Curiositat(R.drawable.gastronomia, "Gastronomia", "la gastronomia ..."))
        llistaCuriositats.add(Curiositat(R.drawable.healthy, "Salut", "la salut ..."))
        llistaCuriositats.add(Curiositat(R.drawable.moda, "Moda", "la moda ..."))
        llistaCuriositats.add(Curiositat(R.drawable.altres, "Altres", "la salut ..."))
    }

    fun getCuriositatByTheme(tema: String): Curiositat? {
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
        var curiositat:Curiositat = Curiositat(imatge,tema,desc)
        llistaCuriositats.add(curiositat)
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