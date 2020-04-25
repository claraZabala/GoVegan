package com.example.govegan.controlador

import android.content.Context
import android.widget.Toast
import com.example.govegan.R
import com.example.govegan.model.*
import com.google.firebase.firestore.FirebaseFirestore

object Controlador {
    private var facadeCarteraCuriositats: FacadeCarteraCuriositats
    private var facadeCarteraIngredients:FacadeCarteraIngredients
    private var facadeCarteraPreguntes:FacadeCarteraPreguntes
    private var facadeCarteraUsuaris: FacadeCarteraUsuaris
    private var facadeCarteraReceptes: FacadeCarteraReceptes
    private var usuariActiu: Usuari?
    private var receptaActiva: Proposta?
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
        receptaActiva = null
        setRecepta = false
        titolReceptaProp = ""
    }

    fun Context.toast(missatge: String){
        Toast.makeText(this, missatge, Toast.LENGTH_LONG).show()
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

    fun setReceptaActiva(recepta: Proposta?){
        receptaActiva = recepta
    }

    fun getReceptaActiva(): Proposta? {
        return receptaActiva
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

    fun addNouIngredientSenseFoto(nomIngredient: String){
        facadeCarteraIngredients.addNouIngredientSenseFoto(nomIngredient)
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

    fun afegirReceptaNova(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,
                          comensals:String, tipusRecepta:Int): Int {
        if (nom.isNullOrEmpty() or pasos.isNullOrEmpty() or tempsPrep.isNullOrEmpty() or
            tempsCuina.isNullOrEmpty() or comensals.isNullOrEmpty()){
            return 1
        }
        facadeCarteraReceptes.addRecepta(nom,pasos,tempsPrep,tempsCuina,comensals,tipusRecepta)
        return 0
    }

    fun afegirIngredientLlistaCompra(ingredient: String): Boolean {
        return facadeCarteraUsuaris.afegirIngredientLlistaCompra(getUsuariActiu()?.nomUsuari,ingredient)
    }

    fun treureIngredientLlistaCompra(ingredient: String):Boolean{
        return facadeCarteraUsuaris.treureIngredientLlistaCompra(getUsuariActiu()?.nomUsuari, ingredient)
    }


    fun getImatgeIngredient(nomIngredient: String):Int{
        return facadeCarteraIngredients.getImatgeIngredient(nomIngredient)
    }

    fun getLlistaIngredientsUsuari():ArrayList<String>?{
        return facadeCarteraUsuaris.getLlistaUsuari(getUsuariActiu()?.nomUsuari)
    }
}