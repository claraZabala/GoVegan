package com.example.govegan.model

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.lang.reflect.Constructor


class Curiositats (imatge:Int,title:String,desc:String) {
    var imatge:Int = 0;
    var title:String = "";
    var desc:String = "";
    init{
        this.imatge = imatge;
        this.title = title;
        this.desc = desc;
    }
}