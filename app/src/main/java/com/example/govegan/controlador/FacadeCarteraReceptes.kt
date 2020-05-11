package com.example.govegan.controlador

import android.widget.ImageView
import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraReceptes
import com.example.govegan.model.Proposta

class FacadeCarteraReceptes(baseDades: BaseDades) {
    var baseDades: BaseDades
    var carteraReceptes: CarteraReceptes
    var controlador: Controlador

    init {
        carteraReceptes = CarteraReceptes()
        this.baseDades = baseDades
        controlador = Controlador
    }

    fun addRecepta(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:Int,ingredients: ArrayList<String>, autor:String): Boolean {
        val recepta = carteraReceptes.addRecepta(nom, pasos, tempsPrep, tempsCuina, comensals, tipusRecepta,autor,ingredients)
        if (recepta == null){
            return false
        }
        controlador.setReceptaActiva(recepta)
        return true
    }

    fun getReceptaByName(nom: String): Proposta? {
        return carteraReceptes.getByName(nom)
    }

    fun getNumPropostes(): Int {
        return carteraReceptes.propostes.size
    }

    fun getImage(position: Int): Int {
        return carteraReceptes.getImage(position)
    }

    fun getTitle(position: Int): CharSequence? {
        return carteraReceptes.getTitle(position)
    }

    fun getTPrep(position: Int): CharSequence? {
        return carteraReceptes.getTPrep(position)
    }

    fun getTCuina(position: Int): CharSequence? {
        return carteraReceptes.getTCuina(position)
    }

    fun getNPax(position: Int): CharSequence? {
        return carteraReceptes.getNPax(position)
    }

    fun setIcona(icona: ImageView, position: Int) {
        carteraReceptes.setIcona(icona,position)
    }

    fun getReceptaByPos(position: Int): Proposta? {
        return carteraReceptes.propostes[position]
    }

}