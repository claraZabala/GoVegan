package com.example.govegan.model


class Propostes (imatge: Int, title: String, temps: String, numPersones: String, icona: Int, ingredients: ArrayList<Ingredient>) {

    var imatge:Int = 0
    var title:String = ""
    var temps:String = ""
    var numPersones:String = ""
    var icona:Int = 0
    private var ingredients: ArrayList<Ingredient>
        get(){
            return ingredients
        }
        set(newIngredients){
            this.ingredients = newIngredients
        }
    init{
        this.imatge = imatge
        this.title = title
        this.temps = temps
        this.numPersones = numPersones
        this.icona = icona
        this.ingredients = ingredients
    }

}