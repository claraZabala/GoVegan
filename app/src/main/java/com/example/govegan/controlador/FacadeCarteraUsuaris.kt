package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraUsuaris
import com.example.govegan.model.Usuari

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
        if (pwd.isBlank() or nomUsuari.isBlank()) {
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
        if (pwd.isBlank() or pwd2.isBlank() or mail.isBlank() or nom.isBlank()
            or edat.isBlank() or cognoms.isBlank() or nomUsuari.isBlank()) {
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

    fun afegirIngredientLlistaCompra(nomUsuari: String?,ingredient: String): Boolean {
        return carteraUsuaris.afegirIngredientLlistaCompra(nomUsuari,ingredient)
    }
    fun treureIngredientLlistaCompra(nomUsuari: String?,ingredient: String):Boolean{
        return carteraUsuaris.treureIngredientLlistaCompra(nomUsuari,ingredient)
    }

    fun getLlistaUsuari(nomUsuari: String?):ArrayList<String>? {
        return carteraUsuaris.getLlistaUsuari(nomUsuari)
    }

    fun afegirInfoPlat(
        usuariActiu: Usuari?,
        dia: String,
        apat: String,
        setmana: String,
        titol: String,
        categoria:Int?
    ) {
        usuariActiu?.setRecepta(dia, apat, setmana, titol,categoria)
    }
}