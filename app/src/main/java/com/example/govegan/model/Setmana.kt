package com.example.govegan.model

class Setmana (usuari: Usuari, id: Int){


    var id: Int = 0
        get() {
            return this.id
        }
        set(value) {
            field = value
        }
    var usuari: Usuari
    var dies: ArrayList<Dia> = ArrayList(7)
        get() {
            return dies
        }



    init {
        this.id = id
        this.usuari = usuari
        dies.add(Dia(id, "dilluns"))
        //TODO:afegir altres dies
    }

    fun getName(): String {
        return """Setmana ${this.id}"""
    }

}