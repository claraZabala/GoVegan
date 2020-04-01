package com.example.govegan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.model.Ingredient
import com.example.govegan.model.Proposta

class AdapterPropostes(context: Context): PagerAdapter() {
    var context:Context = context
    var propostes:List<Proposta> = listOf(Proposta(R.drawable.gastronomia,"Proposta 1","45 min", "2 persones", R.drawable.ou, ArrayList<Ingredient?>()),
        Proposta(R.drawable.healthy,"Proposta 2","10 min", "1 persona", R.drawable.cara, ArrayList<Ingredient?>()),
        Proposta(R.drawable.moda,"Proposta 3","1 hora", "3 persones", R.drawable.ou, ArrayList<Ingredient?>()),
        Proposta(R.drawable.altres,"Proposta 4","1,5 hores", "3 persones", R.drawable.batut, ArrayList<Ingredient?>()))
    init{
        this.context = context

    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return propostes.size
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        var layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.explicacio_proposta,container,false)
        val imageView: ImageView = view.findViewById(R.id.imageRecepta)
        val title: TextView = view.findViewById(R.id.titolRecepta)
        val temps: TextView = view.findViewById(R.id.tempsPreparacio)
        val numPersones: TextView = view.findViewById(R.id.numPersones)
        val icona: ImageView = view.findViewById(R.id.iconRecepta)
        imageView.setImageResource(propostes.get(position).imatge)
        title.setText(propostes.get(position).title)
        temps.setText(propostes.get(position).temps)
        numPersones.setText(propostes.get(position).numPersones)
        icona.setImageResource(propostes.get(position).icona)


        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

    }
}