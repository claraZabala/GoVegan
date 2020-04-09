package com.example.govegan.model

class CarteraIngredients (usuari: Usuari, ingredients: ArrayList<Ingredient>){

    private var ingredients: ArrayList<Ingredient> = ArrayList()
        get(){
            return field
        }
        set(newIngredients){
            field = newIngredients
        }

    init {
        this.ingredients = ingredients
    }

}