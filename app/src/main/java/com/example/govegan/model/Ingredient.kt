package com.example.govegan.model

class Ingredient (nom:String){

    var imatge:Int
        get(){
            return imatge
        }
        set(newImatge){
            this.imatge = newImatge
        }
     var nom:String
        get(){
            return nom
        }
        set(newName){
            this.nom = newName

        }

    /*
    var icones:Array<String>
        get(){
            return icones
        }
        set(newIcones){
            this.icones = newIcones

        }
    */
    init{
        this.nom = nom
        //this.icones = icones
    }


}