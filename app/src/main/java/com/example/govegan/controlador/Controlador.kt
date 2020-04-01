package com.example.govegan.controlador

import com.example.govegan.model.CarteraUsuaris
import com.example.govegan.model.Usuari

import com.example.govegan.model.Curiositat

class Controlador {
    var façanaCarteraCuriositats:FaçanaCarteraCuriositats
    var carteraUsuaris:CarteraUsuaris
    init{
        façanaCarteraCuriositats = FaçanaCarteraCuriositats()
        carteraUsuaris = CarteraUsuaris()
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
                 pwd2: String, edat: String): Int {
        if (pwd.isNullOrBlank() or pwd2.isNullOrBlank() or mail.isNullOrBlank() or nom.isNullOrBlank()
        or edat.isNullOrBlank() or cognoms.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        }
        else if (!pwd.equals(pwd2)){
            return 2
        }
        else {
            if (carteraUsuaris.registre(nom, cognoms, nomUsuari, mail, pwd, edat)){
                return 0
            }
            else {
                return 3
            }
        }
    }

    fun getLlistaCuriositats():ArrayList<Curiositat> {
    return façanaCarteraCuriositats.getLlistaCuriositats()

    }

    fun getCuriositatByTheme(tema: String): Curiositat? {
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