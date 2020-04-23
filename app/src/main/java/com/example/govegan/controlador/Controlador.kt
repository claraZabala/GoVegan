package com.example.govegan.controlador

import android.content.Context
import android.widget.Toast
import com.example.govegan.model.*
import com.google.firebase.firestore.FirebaseFirestore

object Controlador {
    private var facadeCarteraCuriositats: FacadeCarteraCuriositats
    private var facadeCarteraIngredients:FacadeCarteraIngredients
    private var facadeCarteraPreguntes:FacadeCarteraPreguntes
    private var facadeCarteraUsuaris: FacadeCarteraUsuaris
    private var facadeCarteraReceptes: FacadeCarteraReceptes
    private var usuariActiu: Usuari?
    private var setRecepta: Boolean
    private var titolReceptaProp: String
    val baseDades: BaseDades
    val db: FirebaseFirestore

    init {
        db = FirebaseFirestore.getInstance()
        baseDades = BaseDades(db)
        facadeCarteraCuriositats = FacadeCarteraCuriositats(baseDades)
        facadeCarteraIngredients = FacadeCarteraIngredients(baseDades)
        facadeCarteraUsuaris = FacadeCarteraUsuaris(baseDades)
        facadeCarteraPreguntes = FacadeCarteraPreguntes(baseDades)
        facadeCarteraReceptes = FacadeCarteraReceptes(baseDades)
        usuariActiu = null
        setRecepta = false
        titolReceptaProp = ""
    }

    fun getUsuariActiu(): Usuari? {
        return usuariActiu
    }

    fun setUsuariActiu(usuari: Usuari?) {
        usuariActiu = usuari
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String): Int {
        return facadeCarteraUsuaris.registre(nom, cognoms, nomUsuari, mail, pwd, pwd2, edat)
    }

    fun login(nomUsuari: String, pwd: String): Int {
        return facadeCarteraUsuaris.login(nomUsuari, pwd)
    }

    fun getLlistaCuriositats(): ArrayList<Curiositat> {
        return facadeCarteraCuriositats.getLlistaCuriositats()
    }

    fun getCuriositatByTheme(tema: String): Curiositat? {
        return facadeCarteraCuriositats.getCuriositatByTheme(tema)
    }

    fun changeDescCuriositat(tema: String, descNova: String): Boolean {
        return facadeCarteraCuriositats.changeDescCuriositat(tema, descNova)
    }

    fun addCuriositat(tema: String, desc: String, imatge: Int): Boolean {
        return facadeCarteraCuriositats.addCuriositat(tema, desc, imatge)
    }

    fun removeCuriositat(index: Int) {
        return facadeCarteraCuriositats.removeCuriositat(index)
    }


    fun getIndexFromList(tema: String): Int {
        return facadeCarteraCuriositats.getIndexFromList(tema)
    }

    fun getIngredientsByName(nomIngredient: String): Ingredient?{
        return facadeCarteraIngredients.getIngredientsByName(nomIngredient)
    }

    fun getNameIngredients():ArrayList<String>{
        return facadeCarteraIngredients.getNameIngredients()
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        facadeCarteraIngredients.addNouIngredientAmbFoto(nomIngredient,fotoInt)
    }

    fun getAllIngredientsByName():ArrayList<String>{
        return facadeCarteraIngredients.getAllIngredientsByName()
    }

    fun crearPregunta(idUsuari: String, descripcio: String, tema: String){
        facadeCarteraPreguntes.crearPreguntaF(idUsuari, descripcio, tema)
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<Pregunta>?{
        return facadeCarteraPreguntes.mostrarPreguntesPerTemaF(temaP)
    }

    fun crearResposta(tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String){
        facadeCarteraPreguntes.crearRespostaF(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun mostrarRespPerIdPreg(tema:  String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String): ArrayList<Resposta>?{
        return facadeCarteraPreguntes.mostrarRespPerIdPregF(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun setReceptaFromProposta(titolRecepta: String) {
        setRecepta = true
        titolReceptaProp = titolRecepta
    }

    fun getSetRecepta(): Boolean{
        return setRecepta
    }

    fun getTitolRecepta(): String{
        return titolReceptaProp
    }

    fun setDiaRecepta(dia: String, apat: String, setmana: String) {
        //usuariActiu afegir dia, apat, setmana i titolReceptaProp
        //setRecepta = false
    }

    fun Context.toast(missatge: String){
        Toast.makeText(this, missatge, Toast.LENGTH_LONG).show()
    }
}