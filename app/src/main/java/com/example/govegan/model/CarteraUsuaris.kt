package com.example.govegan.model

import com.example.govegan.R
import com.example.govegan.vista.Calendari_Setmanal

class CarteraUsuaris {
    val llistaUsuaris: ArrayList<Usuari> = ArrayList()

    init {
        llistaUsuaris.add(Usuari("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22))
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
                 edat: String): Boolean {
        if (getByID(nomUsuari) != null){
            return false
        }
        /*
        if (pwd no es correcta){
        return false
        }
         */
        var usuariNou = Usuari(nom, cognoms, nomUsuari, mail, pwd, edat.toInt())
        return true
    }

    fun getByID(nomUsuari: String): Usuari? {
        for (item in llistaUsuaris) {
            if (item.nomUsuari.equals(nomUsuari)){
                return item
            }
        }
        return null
    }
}