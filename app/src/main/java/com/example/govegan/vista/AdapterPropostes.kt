package com.example.govegan.vista

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter
import com.example.govegan.R
import com.example.govegan.controlador.Controlador

class AdapterPropostes(context: Context): PagerAdapter() {
    var context:Context = context
    var controlador: Controlador
    //var propostes: ArrayList<Proposta>
    init{
        this.context = context
        controlador = Controlador
        //propostes = controlador.getAllPropostes()
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view.equals(`object`)
    }

    override fun getCount(): Int {
        return controlador.getNumPropostes()
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {
        val layoutInflater = LayoutInflater.from(context)
        val view:View = layoutInflater.inflate(R.layout.explicacio_proposta,container,false)
        val imatge: ImageView = view.findViewById(R.id.imageRecepta)
        val title: TextView = view.findViewById(R.id.titolReceptaP)
        val tempsP: TextView = view.findViewById(R.id.tempsPreparacio)
        val tempsC: TextView = view.findViewById(R.id.tempsCuina)
        val recepta: RelativeLayout = view.findViewById(R.id.relativeRecepta)
        val numPersones: TextView = view.findViewById(R.id.numPersones)
        val icona: ImageView = view.findViewById(R.id.iconRecepta)

        controlador.afegirPropostaLayout(context,position,imatge,title,tempsP,tempsC,numPersones,icona)


        /*imatge.setImageResource(propostes[position].imatge)
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
        }*/
            recepta.setOnClickListener{
            controlador.setReceptaActiva(controlador.getReceptaByPosition(position))
                val intent = Intent(context,Recepta::class.java)
                context.startActivity(intent)
        }
        container.addView(view,0)
        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, objecte: Any) {
        container.removeView(objecte as View?)

    }
}