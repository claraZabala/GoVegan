package com.example.govegan

private class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String,
                     pwd2:String, email:String, edat:Int,
                     calendari: Calendari_Setmanal) {
    var nom:String = ""
    var cognoms:String = ""
    var nomUsuari:String = ""
    var pwd:String = ""
    var pwd2:String = ""
    var email:String = ""
    var edat:Int = 0
    var calendari: Calendari_Setmanal? = null

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.pwd2 = pwd2
        this.email = email
        this.calendari = calendari
    }
}