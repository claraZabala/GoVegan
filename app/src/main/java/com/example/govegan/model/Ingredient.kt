package com.example.govegan.model


class Ingredient (nom:String){


    var imatge:String? = null
     var nom:String

    init{
        this.nom = nom
        //this.icones = icones
    }
    constructor():this(""){

    }
    constructor(nom:String,imatge:String?):this(nom) {
        this.nom = nom
        this.imatge = imatge
    }


    }

