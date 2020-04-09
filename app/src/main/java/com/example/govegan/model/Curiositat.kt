package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Curiositat (imatge:Int, title:String, desc:String) {
    var imatge:Int = 0
        get(){
            return field
        }
        set(newImatge){
            field = newImatge
        }

    var title:String = ""
        get(){
            return field
        }
        set(newTitle){
            field = newTitle
        }

    var desc:String = ""
        get(){
            return field
        }
        set(newDesc){
            field = newDesc
        }

    init{
        this.imatge = imatge
        this.title = title
        this.desc = desc
    }
}