package com.example.govegan.model


class Proposta (imatge: Int, title: String, temps: String, numPersones: String, icona: Int, ingredients: ArrayList<Ingredient?>) {

    var imatge:Int = 0
    var title:String = ""
    var temps:String = ""
    var numPersones:String = ""
    var icona:Int = 0
    private var ingredients: ArrayList<Ingredient?> = TODO()
        get(){
            return ingredients
        }
        set(newIngredients){
            //excepció aqui
            field = newIngredients
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