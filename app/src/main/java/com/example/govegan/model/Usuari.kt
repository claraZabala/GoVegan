package com.example.govegan.model
import com.example.govegan.vista.Calendari_Setmanal

class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int) {
    var nom:String
        get(){
            return nom
        }
        set(newNom){
            this.nom = newNom
        }

    var cognoms:String
        get(){
            return cognoms
        }
        set(newCognoms){
            this.cognoms = newCognoms
        }

    var nomUsuari:String
        get(){
            return nomUsuari
        }
        set(newNomUser){
            this.nomUsuari = newNomUser
        }

    var pwd:String
        get(){
            return pwd
        }
        set(newPWD){
            this.pwd = newPWD
        }

    var edat:Int
        get(){
            return edat
        }
        set(newEdat){
            this.edat = newEdat
        }

    var email:String
        get(){
            return email
        }
        set(newEmail){
            this.email = newEmail
        }

    var calendari:Calendari_Setmanal
        get(){
            return calendari
        }
        set(newCalendari){
            this.calendari = newCalendari
        }

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email
    }
}