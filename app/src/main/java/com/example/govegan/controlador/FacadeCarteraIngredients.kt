package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraIngredients
import com.example.govegan.model.Ingredient

class FacadeCarteraIngredients(baseDades: BaseDades) {

    var carteraIngredients: CarteraIngredients
    var baseDades: BaseDades

    init {
        carteraIngredients = CarteraIngredients()
        this.baseDades = baseDades
    }

    fun getLlistaBaseDades(){
        carteraIngredients.ingredients = baseDades.getAllIngredients()
    }
    fun guardarTotsIngredients(){
        for (i in carteraIngredients.ingredients){
            baseDades.addIngredients(i)
        }
    }


    fun getIngredientsByName(nomIngredient: String): Ingredient?{
        return carteraIngredients.getIngredientsByName(nomIngredient)
    }

    fun getNameIngredients():ArrayList<String>{
        return carteraIngredients.getNameIngredients()
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        baseDades.addIngredients(carteraIngredients.addNouIngredientAmbFoto(nomIngredient,fotoInt))
    }

    fun addNouIngredientSenseFoto(nomIngredient: String){
        baseDades.addIngredients(carteraIngredients.addNouIngredientSenseFoto(nomIngredient))
    }


    fun getAllIngredientsByName():ArrayList<String>{
        return  carteraIngredients.getAllIngredientsByName()
    }

    fun getImatgeIngredient(nomIngredient: String):Int{
        return carteraIngredients.getImatgeIngredient(nomIngredient)
    }
}