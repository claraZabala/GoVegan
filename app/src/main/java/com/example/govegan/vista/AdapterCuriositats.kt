package com.example.govegan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.controlador.Controlador

class AdapterCuriositats(context: Context): PagerAdapter() {
    var context:Context = context
    val controlador:Controlador
    //var curiositats:ArrayList<Curiositat>
    init{
        this.context = context
        controlador = Controlador
        //this.curiositats = curiositats
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return controlador.getNumCuriositats()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.explicacio_curiositat,container,false)
        val imatge:ImageView = view.findViewById(R.id.imageGastronomia)
        val title:TextView = view.findViewById(R.id.titolGastronomia)
        val explicacio:TextView = view.findViewById(R.id.explicacióGastronomia)
        controlador.afegirCuriositatLayout(position,imatge,title,explicacio)
        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

        }
    }



