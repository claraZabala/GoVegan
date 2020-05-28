package com.example.govegan.model

class Setmana (usuari: String, id: Int){

    var id: Int = 0
    var usuari: String
    var dies: MutableMap<String, Dia> = mutableMapOf()

    init {
        this.id = id
        this.usuari = usuari
        dies.put("dilluns", Dia(id, "dilluns"))
        dies.put("dimarts", Dia(id,"dimarts"))
        dies.put("dimecres", Dia(id,"dimecres"))
        dies.put("dijous", Dia(id,"dijous"))
        dies.put("divendres", Dia(id,"divendres"))
        dies.put("dissabte", Dia(id,"dissabte"))
        dies.put("diumenge", Dia(id,"diumenge"))
    }
    constructor():this("",0)

    fun getName(): String {
        return """Setmana ${this.id}"""
    }

    fun recorrerDies(): ArrayList<String> {
        val llistaApats: ArrayList<String> = ArrayList()

        llistaApats.addAll(dies.getValue("dilluns").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("dimarts").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("dimecres").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("dijous").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("divendres").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("dissabte").recorrerApats())
        llistaApats.add("-2")
        llistaApats.addAll(dies.getValue("diumenge").recorrerApats())
        llistaApats.add("-2")

        return llistaApats
    }

}