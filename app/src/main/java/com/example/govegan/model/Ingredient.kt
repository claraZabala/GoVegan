package com.example.govegan.model

class Ingredient (imatge:Int, nom:String, icones:Array<String>){

    private var imatge:Int
        get(){
            return imatge
        }
        set(newImatge){
            this.imatge = newImatge
        }
    private var nom:String
        get(){
            return nom
        }
        set(newName){
            this.nom = newName

        }
    private var icones:Array<String>
        get(){
            return icones
        }
        set(newIcones){
            this.icones = newIcones

        }

    init{
        this.imatge = imatge
        this.nom = nom
        this.icones = icones
    }


}