package com.example.govegan.model

import android.widget.ImageView
import com.example.govegan.R

class CarteraReceptes {
    var propostes: ArrayList<Proposta>

    init {
        propostes = ArrayList()
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:String,autor:String,ingredients:ArrayList<String>): Proposta? {
        val recepta = Proposta(0, nom, tempsPrep, tempsCuina, comensals,tipusRecepta, pasos, ingredients,autor)
        if (getByName(nom) == null) {
            propostes.add(recepta)
            return recepta
        }
        return null
    }

    fun getByName(nom: String): Proposta? {
        var recepta: Proposta? = null
        for (item in propostes) {
            if (item.title.equals(nom)){
                recepta = item
            }
        }
        return recepta
    }

    fun getImage(position: Int): Int {
        return propostes[position].imatge
    }

    fun getTitle(position: Int): String? {
        return propostes[position].title
    }

    fun getTPrep(position: Int): String? {
        return propostes[position].tempsPrep
    }

    fun getTCuina(position: Int): String? {
        return propostes[position].tempsCuina
    }

    fun getNPax(position: Int): String? {
        return propostes[position].numPersones
    }

    fun getAutor(position: Int): String? {
        return propostes[position].autor
    }

    fun getDesc(position: Int): String? {
        return propostes[position].descripcio
    }

    fun setIcona(icona: ImageView, position: Int) {
        var tipus = propostes[position].icona
        if (tipus.equals("0")){
            icona.setImageResource(R.drawable.cara)
        } else if (tipus.equals("1")){
            icona.setImageResource(R.drawable.ou)
        } else if (tipus.equals("2")){
            icona.setImageResource(R.drawable.carn)
        }
    }

    fun getPos(receptaActiva: String?): Int {
        return propostes.indexOf(getByName(receptaActiva!!))
    }

    fun getIcona(receptaActiva: String?): String? {
        return getByName(receptaActiva!!)?.icona
    }

    fun getReceptes(): ArrayList<Proposta> {
        return propostes
    }

    fun getIngredients(receptaActiva: String?): ArrayList<String> {
        return getByName(receptaActiva!!)!!.ingredients
    }
}