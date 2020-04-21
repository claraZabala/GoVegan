package com.example.govegan.model
import com.example.govegan.vista.Calendari_Setmanal



class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int) {


    var nom:String
    var cognoms:String
    var nomUsuari:String
    var pwd:String
    var edat:Int
    var email:String
    lateinit var setmanes: ArrayList<Setmana>
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email

    }

    /*
    * Inicialitza l'atribut llistat de setmanes que conté tota la info de l'usuari
     */
    fun setSetmanesUsuari(setmanes: ArrayList<Setmana>) {
        this.setmanes = setmanes
    }
}