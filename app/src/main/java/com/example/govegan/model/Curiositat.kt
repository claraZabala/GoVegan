package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Curiositat (imatge:Int, title:String, desc:String) {
    var imatge:Int
    var title:String
    var desc:String
    init{
        this.imatge = imatge
        this.title = title
        this.desc = desc
    }
}