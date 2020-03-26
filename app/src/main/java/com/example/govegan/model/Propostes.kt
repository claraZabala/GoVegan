package com.example.govegan.model


class Propostes (imatge:Int,title:String,temps:String,numPersones:String,icona:Int) {

    var imatge:Int = 0
    var title:String = ""
    var temps:String = ""
    var numPersones:String = ""
    var icona:Int = 0
    init{
        this.imatge = imatge
        this.title = title
        this.temps = temps
        this.numPersones = numPersones
        this.icona = icona
    }

}