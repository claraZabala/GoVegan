package com.example.govegan.model
import com.example.govegan.vista.Calendari_Setmanal

class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int) {
    private var nom:String
    var cognoms:String
    var nomUsuari:String
    var pwd:String
    var edat:Int
    var email:String
    var calendari:Calendari_Setmanal

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email
        calendari = Calendari_Setmanal()
    }
}