package com.example.govegan

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.FacadeCarteraReceptes
import com.example.govegan.model.Ingredient
import com.example.govegan.model.Proposta

class AdapterPropostes(context: Context): PagerAdapter() {
    var context:Context = context
    var controlador: Controlador
    var propostes: ArrayList<Proposta>
    init{
        this.context = context
        controlador = Controlador
        propostes = controlador.getAllPropostes()
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
        val title: TextView = view.findViewById(R.id.titolReceptaP)
        val tempsP: TextView = view.findViewById(R.id.tempsPreparacio)
        val tempsC: TextView = view.findViewById(R.id.tempsCuina)
        val numPersones: TextView = view.findViewById(R.id.numPersones)
        val icona: ImageView = view.findViewById(R.id.iconRecepta)
        imageView.setImageResource(propostes.get(position).imatge)
        title.setText(propostes.get(position).title)
        tempsP.setText(propostes.get(position).tempsPrep)
        tempsC.setText(propostes.get(position).tempsCuina)
        numPersones.setText(propostes.get(position).numPersones)
        val tipus = propostes.get(position).icona
        if (tipus == 0){
            icona.setImageResource(R.drawable.cara)
        } else if (tipus == 1){
            icona.setImageResource(R.drawable.ou)
        } else if (tipus == 2){
            icona.setImageResource(R.drawable.carn)
        }

        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

    }
}