package com.example.govegan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.model.Curiositat

class AdapterCuriositats(context: Context): PagerAdapter() {
    var context:Context = context
    var curiositats:List<Curiositat> = listOf(Curiositat(R.drawable.gastronomia,"Gastronomia","la gastronomia ..."),Curiositat(R.drawable.healthy,"Salut","la salut ..."),Curiositat(R.drawable.moda,"Moda","la moda ..."),Curiositat(R.drawable.altres,"Altres","la salut ..."))
    init{
        this.context = context

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return curiositats.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.explicacio_curiositat,container,false)
        val imageView:ImageView = view.findViewById(R.id.imageGastronomia)
        val title:TextView = view.findViewById(R.id.titolGastronomia)
        val explicacio:TextView = view.findViewById(R.id.explicacióGastronomia)
        imageView.setImageResource(curiositats.get(position).imatge)
        title.setText(curiositats.get(position).title)
        explicacio.setText(curiositats.get(position).desc)

        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

        }
    }



