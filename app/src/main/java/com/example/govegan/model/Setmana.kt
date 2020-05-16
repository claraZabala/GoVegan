package com.example.govegan.model

class Setmana (usuari: String, id: Int){

    var id: Int = 0
    var usuari: String
    var dies: MutableMap<String,Dia> = mutableMapOf()

    init {
        this.id = id
        this.usuari = usuari
        dies.put("dilluns",Dia(id, "dilluns"))
        dies.put("dimarts",Dia(id,"dimarts"))
        dies.put("dimecres",Dia(id,"dimecres"))
        dies.put("dijous",Dia(id,"dijous"))
        dies.put("divendres",Dia(id,"divendres"))
        dies.put("dissabte",Dia(id,"dissabte"))
        dies.put("diumenge",Dia(id,"diumenge"))
    }
    constructor():this("",0)

    fun getName(): String {
        return """Setmana ${this.id}"""
    }

    fun recorrerDies(): ArrayList<String> {
        val llistaApats: ArrayList<String> = ArrayList()
        for (i in dies) {
            llistaApats.addAll(i.value.recorrerApats())
            llistaApats.add("-2")
        }
        return llistaApats
    }

}