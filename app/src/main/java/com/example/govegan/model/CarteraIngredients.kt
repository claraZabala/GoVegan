package com.example.govegan.model

class CarteraIngredients (){

    var ingredients: ArrayList<Ingredient> = ArrayList()
        get(){
            return field
        }
        set(newIngredients){
            field = newIngredients
        }

    init {
    }

    fun getIngredientsByName(nomIngredient: String):Ingredient?{
        for (i in ingredients){
            if(i.nom.equals(String()))
                return i
        }
        return null
    }

    fun getNameIngredients():ArrayList<String>{
        var nomIngredients: ArrayList<String> = ArrayList()
        for (i in ingredients){
            nomIngredients.add(i.nom)
        }
        return nomIngredients
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        var nouIngredient:Ingredient = Ingredient(nomIngredient)
        nouIngredient.imatge = fotoInt
        ingredients.add(nouIngredient)
    }

    fun addNouIngredientSenseFoto(nomIngredient: String){
        var nouIngredient:Ingredient = Ingredient(nomIngredient)
        ingredients.add(nouIngredient)
    }





}