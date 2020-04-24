package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {


    val idSetmana: Int
    var nom: String
        get() = nom
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

    fun afegirApat(apat: String, titolRecepta: String) {
        when (apat) {
            "esmorzar" -> this.esmorzar.put(0, titolRecepta)
            "dinar" -> this.dinar.put(0, titolRecepta)
            "sopar" -> this.sopar.put(0, titolRecepta)
            else -> print("caca")
        }

    }

}