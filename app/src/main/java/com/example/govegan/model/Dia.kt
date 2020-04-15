package com.example.govegan.model

class Dia(idSetmana: Int) {

    val idSetmana: Int
    var esmorzar: MutableMap<Int, String>
    init {
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
    }

}