package com.example.govegan.model
import com.example.govegan.R
class CarteraIngredients (){

    var ingredients: ArrayList<Ingredient> = ArrayList()
        get(){
            return field
        }
        set(newIngredients){
            field = newIngredients
        }

    init {
        ingredients.add(Ingredient("llentia",R.drawable.llentia))
        ingredients.add(Ingredient("maduixa",R.drawable.maduixa))
        ingredients.add(Ingredient("brocoli",R.drawable.brocoli))
        ingredients.add(Ingredient("xocolata",R.drawable.xocolata))

    }

    fun getIngredientsByName(nomIngredient: String):Ingredient?{
        for (i in ingredients){
            if(i.nom.equals(String()))
                return i
        }
        return null
    }

    fun getAllIngredientsByName():ArrayList<String>{
        var nomIngredients: ArrayList<String> = ArrayList()
        for (i in ingredients){
            nomIngredients.add(i.nom)
        }
        return nomIngredients
    }

    fun getNameIngredients():ArrayList<String>{
        var nomIngredients: ArrayList<String> = ArrayList()
        for (i in ingredients){
            nomIngredients.add(i.nom)
        }
        return nomIngredients
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        var nouIngredient:Ingredient = Ingredient(nomIngredient,fotoInt)
        ingredients.add(nouIngredient)
    }






}