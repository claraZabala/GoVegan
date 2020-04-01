package com.example.govegan.model

class CarteraIngredients (usuari: Usuari, ingredients: ArrayList<Ingredient>){

    private var ingredients: ArrayList<Ingredient>
        get(){
            return ingredients
        }
        set(newIngredients){
            this.ingredients = newIngredients
        }

    private var usuari: Usuari
        get(){
            return usuari
        }
        set(newUsuari){
            this.usuari = newUsuari
        }

    init {
        this.ingredients = ingredients
        this.usuari = usuari
    }

}