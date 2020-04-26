package com.example.govegan.model


class Proposta (imatge: Int, title: String, tempsPrep: String, tempsCuina: String, numPersones: String,
                icona: Int, descripcio:String, ingredients: ArrayList<Ingredient?>) {

    var imatge:Int = 0
    var title:String = ""
    var tempsPrep:String = ""
    var tempsCuina:String = ""
    var numPersones:String = ""
    var icona:Int = 0
    var descripcio:String = ""
    var ingredients: ArrayList<Ingredient?>

    init{
        this.imatge = imatge
        this.title = title
        this.tempsPrep = tempsPrep
        this.tempsCuina = tempsCuina
        this.numPersones = numPersones
        this.icona = icona
        this.ingredients = ingredients
        this.descripcio = descripcio
    }

}