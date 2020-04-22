package com.example.govegan.controlador

import com.example.govegan.model.CarteraIngredients
import com.example.govegan.model.Ingredient

class FaçanaCarteraIngredients() {

    var carteraIngredients: CarteraIngredients

    init {
        carteraIngredients = CarteraIngredients()
    }

    fun getIngredientsByName(nomIngredient: String): Ingredient?{
        return carteraIngredients.getIngredientsByName(nomIngredient)
    }

    fun getNameIngredients():ArrayList<String>{
        return carteraIngredients.getNameIngredients()
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        carteraIngredients.addNouIngredientAmbFoto(nomIngredient,fotoInt)
    }
    fun getAllIngredientsByName():ArrayList<String>{
        return  carteraIngredients.getAllIngredientsByName()
    }
}