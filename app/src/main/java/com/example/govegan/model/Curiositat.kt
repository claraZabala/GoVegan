package com.example.govegan.model


class Curiositat (imatge:String?, title:String, desc:String) {
    var imatge:String? = null

    var title:String = ""

    var desc:String = ""

    init{
        this.imatge = imatge
        this.title = title
        this.desc = desc
    }

    constructor():this(null,"",""){

    }
}