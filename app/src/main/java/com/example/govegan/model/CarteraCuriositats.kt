package com.example.govegan.model

class CarteraCuriositats {
    var llistaCuriositats: ArrayList<Curiositat> = ArrayList()

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
    fun addCuriositat(tema:String,desc:String,imatge:String?):Curiositat?{
        for (item in llistaCuriositats) {
            if (item.title.equals(tema)){
                return null
            }
        }
        val curiositat = Curiositat(imatge,tema,desc)
        llistaCuriositats.add(curiositat)
        return curiositat
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


    fun getTitle(position: Int): String {
        return llistaCuriositats[position].title
    }

    fun getDescripcio(position: Int): String {
        return llistaCuriositats[position].desc
    }
}