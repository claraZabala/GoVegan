package com.example.govegan

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.startActivity
import androidx.core.view.get
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.controlador.Controlador
import com.example.govegan.model.Proposta
import com.example.govegan.vista.Recepta

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
        val layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.explicacio_proposta,container,false)
        val imageView: ImageView = view.findViewById(R.id.imageRecepta)
        val title: TextView = view.findViewById(R.id.titolReceptaP)
        val tempsP: TextView = view.findViewById(R.id.tempsPreparacio)
        val tempsC: TextView = view.findViewById(R.id.tempsCuina)
        val layout: RelativeLayout = view.findViewById(R.id.relativeRecepta)
        val numPersones: TextView = view.findViewById(R.id.numPersones)
        val icona: ImageView = view.findViewById(R.id.iconRecepta)
        imageView.setImageResource(propostes[position].imatge)
        title.text = propostes[position].title
        tempsP.text = propostes[position].tempsPrep
        tempsC.text = propostes[position].tempsCuina
        numPersones.text = propostes[position].numPersones
        val tipus = propostes[position].icona
        if (tipus == 0){
            icona.setImageResource(R.drawable.cara)
        } else if (tipus == 1){
            icona.setImageResource(R.drawable.ou)
        } else if (tipus == 2){
            icona.setImageResource(R.drawable.carn)
        }
            layout.setOnClickListener{
            controlador.setReceptaActiva(propostes.get(position))
                var intent = Intent(context,Recepta::class.java)
                context.startActivity(intent)
        }
        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

    }
}