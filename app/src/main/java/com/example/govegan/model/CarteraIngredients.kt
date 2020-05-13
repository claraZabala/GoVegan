package com.example.govegan.model
import com.example.govegan.R
class CarteraIngredients {

    var ingredients: ArrayList<Ingredient> = ArrayList()

    init {
        /*
        ingredients.add(Ingredient("llentia",R.drawable.llentia))
        ingredients.add(Ingredient("maduixa",R.drawable.maduixa))
        ingredients.add(Ingredient("brocoli",R.drawable.brocoli))
        ingredients.add(Ingredient("xocolata",R.drawable.xocolata))
        ingredients.add(Ingredient("batut",R.drawable.batut))
        ingredients.add(Ingredient("ou",R.drawable.ou))
        ingredients.add(Ingredient("arros",R.drawable.arros))
        ingredients.add(Ingredient("macarrons",R.drawable.macarrons))
        ingredients.add(Ingredient("pa",R.drawable.pa))
        ingredients.add(Ingredient("pebrot",R.drawable.pebrot))
        ingredients.add(Ingredient("tomaquet",R.drawable.tomaquet))
        ingredients.add(Ingredient("ceba",R.drawable.ceba))
        */


    }

    fun getIngredientsByName(nomIngredient: String):Ingredient?{
        for (i in ingredients){
            if(i.nom.equals(String()))
                return i
        }
        return null
    }

    fun getAllIngredientsByName():ArrayList<String>{
        val nomIngredients: ArrayList<String> = ArrayList()
        for (i in ingredients){
            nomIngredients.add(i.nom)
        }
        return nomIngredients
    }

    fun getNameIngredients():ArrayList<String>{
        val nomIngredients: ArrayList<String> = ArrayList()
        for (i in ingredients){
            nomIngredients.add(i.nom)
        }
        return nomIngredients
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int):Ingredient{
        val nouIngredient:Ingredient = Ingredient(nomIngredient,fotoInt)
        ingredients.add(nouIngredient)
        return nouIngredient
    }


    fun addNouIngredientSenseFoto(nomIngredient: String):Ingredient{
        val nouIngredient:Ingredient = Ingredient(nomIngredient)
        ingredients.add(nouIngredient)
        return nouIngredient
    }
    
    fun getImatgeIngredient(nomIngredient: String):Int{
        for (i in ingredients){
            if(i.nom.equals(nomIngredient))
                if(i.imatge != null)
                    return  i.imatge
        }
        return 0

    }






}