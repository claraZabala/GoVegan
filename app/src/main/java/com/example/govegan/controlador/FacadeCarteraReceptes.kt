package com.example.govegan.controlador

import android.widget.ImageView
import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraReceptes
import com.example.govegan.model.Proposta

class FacadeCarteraReceptes(baseDades: BaseDades) {
    var baseDades: BaseDades
    var carteraReceptes: CarteraReceptes = CarteraReceptes()
    var controlador: Controlador

    init {
        this.baseDades = baseDades
        controlador = Controlador
    }

    fun addRecepta(lastpath:String?,nom: String, pasos: String, tempsPrep: String, tempsCuina: String,comensals:String,tipusRecepta:String,ingredients: ArrayList<String>, autor:String): Proposta? {
        val proposta = carteraReceptes.addRecepta(lastpath,nom, pasos, tempsPrep, tempsCuina, comensals,
            tipusRecepta, autor, ingredients)
            ?: return null
        controlador.setReceptaActiva(nom)
        return proposta
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
    fun getPath(position: Int): String? {
        return carteraReceptes.getPath(position)
    }
    fun getTitle(position: Int): String? {
        return carteraReceptes.getTitle(position)
    }

    fun getTPrep(position: Int): String? {
        return carteraReceptes.getTPrep(position)
    }

    fun getTCuina(position: Int): String? {
        return carteraReceptes.getTCuina(position)
    }

    fun getNPax(position: Int): String? {
        return carteraReceptes.getNPax(position)
    }

    fun setIcona(icona: ImageView, position: Int) {
        carteraReceptes.setIcona(icona,position)
    }


    fun getPos(receptaActiva: String?): Int {
        return carteraReceptes.getPos(receptaActiva)
    }

    fun getAutor(position: Int): String? {
        return carteraReceptes.getAutor(position)
    }

    fun getDesc(position: Int): CharSequence? {
        return carteraReceptes.getDesc(position)
    }

    fun getIcona(receptaActiva: String?): String? {
        return carteraReceptes.getIcona(receptaActiva)
    }

    fun getAllReceptes(): ArrayList<Proposta> {
        return carteraReceptes.getReceptes()
    }

    fun getIngredients(receptaActiva: String?): ArrayList<String> {
        return carteraReceptes.getIngredients(receptaActiva)
    }

    fun getLlistaBaseDades() {
        carteraReceptes.propostes = baseDades.getAllPropostes()
    }
}