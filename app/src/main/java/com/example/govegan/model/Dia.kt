package com.example.govegan.model

class Dia(idSetmana: Int) {

    val idSetmana: Int
    var esmorzar: MutableMap<Int, String>
    var dinar: MutableMap<Int, String>
    var sopar: MutableMap<Int, String>


    init {
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
        this.dinar = mutableMapOf()
        this.sopar = mutableMapOf()
    }

}