package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraUsuaris

class FacadeCarteraUsuaris (baseDades: BaseDades) {
    var carteraUsuaris: CarteraUsuaris
    var controlador: Controlador
    var baseDades: BaseDades

    init{
        carteraUsuaris = CarteraUsuaris(baseDades)
        controlador = Controlador
        this.baseDades = baseDades
    }

    fun login(nomUsuari: String, pwd: String): Int {
        if (pwd.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        } else {
            if (carteraUsuaris.login(nomUsuari, pwd)) {
                //es porta a memòria la info de l'usuari (les seves setmanes)
                controlador.setUsuariActiu(carteraUsuaris.setUsuariActiu(nomUsuari))
                if (controlador.getUsuariActiu() == null) println("Usuari null")
                return 0
            }
            else{
                return 2
            }
        }
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
                 pwd2: String, edat: String): Int {
        if (pwd.isNullOrBlank() or pwd2.isNullOrBlank() or mail.isNullOrBlank() or nom.isNullOrBlank()
            or edat.isNullOrBlank() or cognoms.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        } else if (!pwd.equals(pwd2)) {
            return 2
        } else {
            if (carteraUsuaris.registre(nom, cognoms, nomUsuari, pwd, mail, edat)) {
                return 0
            } else {
                return 3
            }
        }
    }
}