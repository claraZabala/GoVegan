package com.example.govegan.model

class Setmana (usuari: Usuari, id: Int){

    var id: Int = 0
    var usuari: Usuari
    var dies: ArrayList<Dia> = ArrayList(7)

    init {
        this.id = id
        this.usuari = usuari
        dies.add(Dia(id, "dilluns"))
        dies.add(Dia(id,"dimarts"))
        dies.add(Dia(id,"dimecres"))
        dies.add(Dia(id,"dijous"))
        dies.add(Dia(id,"divendres"))
        dies.add(Dia(id,"dissabte"))
        dies.add(Dia(id,"diumenge"))
    }

    fun getName(): String {
        return """Setmana ${this.id}"""
    }

    fun recorrerDies(): ArrayList<String> {
        val llistaApats: ArrayList<String> = ArrayList()
        for (i in dies) {
            llistaApats.addAll(i.recorrerApats())
            llistaApats.add("-2")
        }
        return llistaApats
    }

}