package com.example.govegan.model

class Proposta (lastPath:String?, title: String, tempsPrep: String, tempsCuina: String, numPersones: String,
                icona: String, descripcio:String, ingredients: ArrayList<String>, autor:String) {

    var imatge:Int = 0
    var title:String = ""
    var tempsPrep:String = ""
    var tempsCuina:String = ""
    var numPersones:String = ""
    var icona:String = "0"
    var descripcio:String = ""
    var ingredients: ArrayList<String>
    var autor: String = ""
    var lastPath:String? = null

    init{
        this.title = title
        this.tempsPrep = tempsPrep
        this.tempsCuina = tempsCuina
        this.numPersones = numPersones
        this.icona = icona
        this.ingredients = ingredients
        this.descripcio = descripcio
        this.autor = autor
        this.lastPath = lastPath
    }
    constructor():this(null,"","","","", "0", "", ArrayList<String>(), "")

}