package com.example.govegan.controlador

import com.example.govegan.model.CarteraUsuaris
import com.example.govegan.model.Usuari

class Controlador {
    var carteraUsuaris = CarteraUsuaris();
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

    init{

    }

}