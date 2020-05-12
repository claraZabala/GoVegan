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
    private var receptaActiva: Proposta?
    private var isFromProposta: Boolean
    private var titolReceptaProp: String
    val baseDades: BaseDades
    val db: FirebaseFirestore


    /**
     * INITS *********************
     *      **********************
     */

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
        isFromProposta = false
        titolReceptaProp = ""
    }
    fun actualizarUsuariActiu(){
        baseDades.actualitzarUsuariActiu()

    }

    fun Context.toast(missatge: String){
        Toast.makeText(this, missatge, Toast.LENGTH_LONG).show()
    }

    /**
     * USER *********************
     *      **********************
     */

    fun getUsuariActiu(): Usuari? {
        return usuariActiu
    }

    fun setUsuariActiu(usuari: Usuari?) {
        usuariActiu = usuari
        facadeCarteraUsuaris.carregarUsuari(usuariActiu)
    }

    fun registre(userID:String?, nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String): Int {
        return facadeCarteraUsuaris.registre(userID,nom, cognoms, nomUsuari, mail, pwd, pwd2, edat)
    }

    fun login(ID:String) {
        return facadeCarteraUsuaris.login(ID)
    }

    /**
     * RECEPTA *********************
     *         **********************
     */

    fun setReceptaActiva(recepta: Proposta?){
        receptaActiva = recepta
    }

    fun getReceptaActiva(): Proposta? {
        return receptaActiva
    }

    fun setReceptaFromProposta(titolRecepta: String) {
        isFromProposta = true
        titolReceptaProp = titolRecepta
    }

    fun setTitolReceptaFromCalendari(titol: String) {
        this.titolReceptaProp = titol
    }

    fun getIsFromProposta(): Boolean{
        return isFromProposta
    }
    fun setIsFromProposta(setRecepta:Boolean){
        this.isFromProposta = setRecepta
    }

    //usuariActiu afegir dia, apat, setmana i titol
    fun setDiaRecepta(dia: String, apat: String, setmana: String,categoria:String?) {
        facadeCarteraUsuaris.afegirInfoPlat(usuariActiu, dia, apat, setmana, titolReceptaProp,categoria)
        isFromProposta = false
        actualizarUsuariActiu()

    }

    fun getCategoriaApatDia(setmana:String,dia:String,apat:String):String?{
        return usuariActiu?.getCategoriaApatDia(setmana,dia,apat)
    }

    fun afegirReceptaNova(nom: String, pasos: String, tempsPrep: String, tempsCuina: String,
                          comensals:String, tipusRecepta:String, ingredients: ArrayList<String>): Int {
        if (nom.isEmpty() or pasos.isEmpty() or tempsPrep.isEmpty() or
            tempsCuina.isEmpty() or comensals.isEmpty() or ingredients.isNullOrEmpty()){
            return 1
        }
        if (facadeCarteraReceptes.addRecepta(nom,pasos,tempsPrep,tempsCuina,comensals,tipusRecepta,ingredients, usuariActiu?.nomUsuari!!)){
            return 0
        }
        return 2
    }

    fun getReceptaByName(nom: String): Proposta? {
        val recepta = facadeCarteraReceptes.getReceptaByName(nom)
        setReceptaActiva(recepta)
        return recepta
    }

    fun getAllPropostes(): ArrayList<Proposta> {
        return facadeCarteraReceptes.getAllPropostes()
    }

    /**
     * CURIOSITATS *********************
     *             **********************
     */

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

    /**
     * INGREDIENTS *********************
     *             **********************
     */

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

    fun afegirIngredientLlistaCompra(ingredient: String): Boolean {
        if(facadeCarteraUsuaris.afegirIngredientLlistaCompra(getUsuariActiu()?.nomUsuari,ingredient)) {
            actualizarUsuariActiu()
            return true
        }
        return false
    }

    fun treureIngredientLlistaCompra(ingredient: String):Boolean{
        if(facadeCarteraUsuaris.treureIngredientLlistaCompra(getUsuariActiu()?.nomUsuari, ingredient)){
            actualizarUsuariActiu()
            return true
        }
        return false
    }


    fun getImatgeIngredient(nomIngredient: String):Int{
        return facadeCarteraIngredients.getImatgeIngredient(nomIngredient)
    }

    fun getLlistaIngredientsUsuari():ArrayList<String>?{
        return facadeCarteraUsuaris.getLlistaUsuari(getUsuariActiu()?.nomUsuari)
    }

    /**
     * FORUM *********************
     *      **********************
     */

    fun crearPregunta(descripcio: String, tema: String){
        facadeCarteraPreguntes.crearPreguntaF(usuariActiu!!.nomUsuari, descripcio, tema)
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<String>?{
        return facadeCarteraPreguntes.mostrarPreguntesPerTemaF(temaP)
    }

    fun crearResposta(tema: String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String){
        facadeCarteraPreguntes.crearRespostaF(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun mostrarRespPerIdPreg(tema:  String, descripcio: String, esCertificat: Boolean, idUsuari: String, idDestinatari: String): ArrayList<Resposta>?{
        return facadeCarteraPreguntes.mostrarRespPerIdPregF(tema, descripcio, esCertificat, idUsuari, idDestinatari)
    }

    fun getContadorPreguntes(descripcio: String, tema: String): Int{
        return facadeCarteraPreguntes.getCont(usuariActiu?.nomUsuari!!, descripcio, tema)
    }
}