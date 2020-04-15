package com.example.govegan.model

class Setmana (usuari: Usuari, id: Int){
    val id: Int
    var usuari: Usuari
    var dies: ArrayList<Dia> = ArrayList(7)

    init {
        this.id = id
        this.usuari = usuari
        dies.add(Dia(id))
    }
}