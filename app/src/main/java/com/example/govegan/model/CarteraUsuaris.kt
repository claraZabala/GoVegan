package com.example.govegan.model

import com.example.govegan.R
import com.example.govegan.vista.Calendari_Setmanal
import com.google.firebase.firestore.FirebaseFirestore

class CarteraUsuaris {
    val llistaUsuaris: ArrayList<Usuari> = ArrayList()
    val baseDades: BaseDades
    val db: FirebaseFirestore

    init {
        llistaUsuaris.add(Usuari("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22))
        llistaUsuaris.add(Usuari("Clara", "Zabala", "czaba", "kkdlvkflk25", "claris99@gmail.com", 20))
        llistaUsuaris.add(Usuari("LLuis", "Roca", "lluis", "lluis", "lluis@gmail.com", 20))
        db = FirebaseFirestore.getInstance()
        baseDades = BaseDades(db)
        baseDades.addUser("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22)
        baseDades.addUser("Clara", "Zabala", "czaba", "kkdlvkflk25", "claris99@gmail.com", 20)
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, pwd: String, mail: String,
                 edat: String): Boolean {
        if (getByID(nomUsuari) != null){
            return false
        }
        if (pwd.length < 4){
        return false
        }
        var usuariNou = Usuari(nom, cognoms, nomUsuari, pwd, mail, edat.toInt())
        llistaUsuaris.add(usuariNou)
        baseDades.addUser(nom,cognoms,nomUsuari,pwd,mail,edat.toInt())
        var hola = baseDades.getUserByID(nomUsuari)
        return true
    }

    fun login(nomUsuari: String, pwd: String): Boolean {
        var user = getByID(nomUsuari)
        if (user == null) {
            return false
        } else if(!user.pwd.equals(pwd)){
            return false
        }
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

    fun setUsuariActiu(nomUsuari: String): Usuari? {
        return getByID(nomUsuari)
    }
}