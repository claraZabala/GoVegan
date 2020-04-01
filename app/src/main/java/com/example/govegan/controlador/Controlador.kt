package com.example.govegan.controlador

import com.example.govegan.model.CarteraUsuaris
import com.example.govegan.model.Curiositat
import com.example.govegan.model.Usuari

class Controlador {
    var facadeCarteraCuriositats: FacadeCarteraCuriositats
    var carteraUsuaris: CarteraUsuaris
    var usuariActiu: Usuari?

    init {
        facadeCarteraCuriositats = FacadeCarteraCuriositats()
        carteraUsuaris = CarteraUsuaris()
        usuariActiu = null
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String): Int {
        if (pwd.isNullOrBlank() or pwd2.isNullOrBlank() or mail.isNullOrBlank() or nom.isNullOrBlank()
            or edat.isNullOrBlank() or cognoms.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        } else if (!pwd.equals(pwd2)) {
            return 2
        } else {
            if (carteraUsuaris.registre(nom, cognoms, nomUsuari, mail, pwd, edat)) {
                return 0
            } else {
                return 3
            }
        }
    }

    fun getLlistaCuriositats(): ArrayList<Curiositat> {
        return facadeCarteraCuriositats.getLlistaCuriositats()
    }

    fun getCuriositatByTheme(tema: String): Curiositat? {
        return facadeCarteraCuriositats.getCuriositatByTheme(tema)
    }

    fun changeDescCuriositat(tema: String, descNova: String): Boolean {
        return facadeCarteraCuriositats.changeDescCuriositat(tema, descNova)
    }

    fun addCuriositat(tema: String, desc: String, imatge: Int): Boolean {
        return facadeCarteraCuriositats.addCuriositat(tema, desc, imatge)
    }

    fun removeCuriositat(index: Int) {
        return facadeCarteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema: String): Int {
        return facadeCarteraCuriositats.getIndexFromList(tema)
    }

}