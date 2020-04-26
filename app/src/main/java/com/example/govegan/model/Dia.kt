package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {


    val idSetmana: Int
    var nom: String
    var esmorzar: MutableMap<Int, String>
    var dinar: MutableMap<Int, String>
    var sopar: MutableMap<Int, String>


    init {
        this.nom = nom
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
        this.dinar = mutableMapOf()
        this.sopar = mutableMapOf()
    }

    /*
    * Afegeix als Diccionaris el titol del plat junt amb un Int
    * vegà ---> 0
    * vegetarià ---> 1
    * carn ---> 2
     */
    fun afegirApat(apat: String, titolRecepta: String) {
        when (apat) {
            //TODO: gestionar el Int (ara és sempre 0 per defecte)
            "esmorzar" -> this.esmorzar.put(0, titolRecepta)
            "dinar" -> this.dinar.put(0, titolRecepta)
            "sopar" -> this.sopar.put(0, titolRecepta)
            else -> print("caca")
        }

    }

}