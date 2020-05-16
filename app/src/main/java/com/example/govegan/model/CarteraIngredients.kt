package com.example.govegan.model

class CarteraIngredients {

    var ingredients: ArrayList<Ingredient> = ArrayList()

    init {
        ingredients.add(Ingredient("llentia","llentia.png"))
        ingredients.add(Ingredient("maduixa","maduixa.png"))
        ingredients.add(Ingredient("brocoli","brocoli.png"))
        ingredients.add(Ingredient("xocolata","xocolata.png"))
        ingredients.add(Ingredient("arros","arros.jpg"))
        ingredients.add(Ingredient("macarrons","macarrons.ppg"))
        ingredients.add(Ingredient("pa","pa.jpg"))
        ingredients.add(Ingredient("pebrot","pebrot.jpg"))
        ingredients.add(Ingredient("tomaquet","tomaquet.jpeg"))
        ingredients.add(Ingredient("ceba","ceba.jpg"))
        ingredients.add(Ingredient("pastanaga","pastanaga.jpg"))
        ingredients.add(Ingredient("carbassó","carbasso.jpg"))
        ingredients.add(Ingredient("quinoa","quinoa.jpg"))
        ingredients.add(Ingredient("cuscus","cuscus.jpg"))
        ingredients.add(Ingredient("espinacs","espinacs.jpg"))
        ingredients.add(Ingredient("Llet de soja","lletSoja.jpg"))
        ingredients.add(Ingredient("champinyons","champi.jpg"))
        ingredients.add(Ingredient("oli d'oliva","oli.jpg"))
        ingredients.add(Ingredient("blat de moro","blat.jpg"))
        ingredients.add(Ingredient("sucre","sucre.jpg"))
        ingredients.add(Ingredient("curcuma","curcuma.jpg"))



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

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: String?):Ingredient{
        val nouIngredient:Ingredient = Ingredient(nomIngredient,fotoInt)
        ingredients.add(nouIngredient)
        return nouIngredient
    }


    fun addNouIngredientSenseFoto(nomIngredient: String):Ingredient{
        val nouIngredient:Ingredient = Ingredient(nomIngredient)
        ingredients.add(nouIngredient)
        return nouIngredient
    }
    
    fun getImatgeIngredient(nomIngredient: String):String?{
        for (i in ingredients){
            if(i.nom.equals(nomIngredient))
                if(i.imatge != null)
                    return  i.imatge
        }
        return null

    }






}