package com.example.govegan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter

class AdapterPropostes(context: Context): PagerAdapter() {
    var context:Context = context;
    var propostes:List<Propostes> = listOf(Propostes(R.drawable.gastronomia,"Gastronomia","la gastronomia ..."),Propostes(R.drawable.healthy,"Salut","la salut ..."),Propostes(R.drawable.moda,"Moda","la moda ..."),Propostes(R.drawable.altres,"Altres","la salut ..."))
    init{
        this.context = context;

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`);
    }

    override fun getCount(): Int {
        return propostes.size;
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var layoutInflater = LayoutInflater.from(context);
        val view:View = layoutInflater.inflate(R.layout.explicacio_curiositat,container,false);
        val imageView: ImageView = view.findViewById(R.id.imageGastronomia)
        val title: TextView = view.findViewById(R.id.titolGastronomia)
        val explicacio: TextView = view.findViewById(R.id.explicacióGastronomia)
        imageView.setImageResource(propostes.get(position).imatge)
        title.setText(propostes.get(position).title)
        explicacio.setText(propostes.get(position).desc)

        container.addView(view,0)
        return view;
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?);

    }
}