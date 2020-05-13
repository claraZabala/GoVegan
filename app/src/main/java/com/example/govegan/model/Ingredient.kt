package com.example.govegan.model


class Ingredient (nom:String){


    var imatge:Int = 0
     var nom:String

    init{
        this.nom = nom
        //this.icones = icones
    }
    constructor():this(""){

    }
    constructor(nom:String,imatge:Int):this(nom) {
        this.nom = nom
        this.imatge = imatge
    }


    }

