package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Curiositat (imatge:Int, title:String, desc:String) {
    var imatge:Int
        get(){
            return imatge
        }
        set(newImatge){
            this.imatge = newImatge
        }
    var title:String
        get(){
            return title
        }
        set(newTitle){
            this.title = newTitle

        }
    var desc:String
        get(){
            return desc
        }
        set(newDesc){
            this.desc = newDesc

        }
    init{
        this.imatge = imatge
        this.title = title
        this.desc = desc
    }
}