package com.example.govegan

import android.content.Context
import android.media.Image
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.model.Curiositats

class AdapterCuriositats(context: Context,curiositats:ArrayList<Curiositats>): PagerAdapter() {
    var context:Context = context
    var curiositats:ArrayList<Curiositats>
    init{
        this.context = context
        this.curiositats = curiositats
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



