package com.example.govegan.controlador

import android.content.Context
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.govegan.model.*
import com.google.firebase.firestore.FirebaseFirestore

object Controlador {
    private var facadeCarteraCuriositats: FacadeCarteraCuriositats
    private var facadeCarteraIngredients: FacadeCarteraIngredients
    private var facadeCarteraPreguntes: FacadeCarteraPreguntes
    private var facadeCarteraUsuaris: FacadeCarteraUsuaris
    private var facadeCarteraReceptes: FacadeCarteraReceptes
    private var usuariActiu: String?
    private var receptaActiva: String?
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
        //facadeCarteraUsuaris.initUsers(baseDades.getAllUsers())
    }

    fun Context.toast(missatge: String) {
        Toast.makeText(this, missatge, Toast.LENGTH_LONG).show()
    }

    /**
     * USER *********************
     *      **********************
     */

    fun getUsuariActiu(): String? {
        return usuariActiu
    }

    fun setUsuariActiu(usuari: String?) {
        usuariActiu = usuari
    }

    fun registre(
        nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String
    ): Int {
        return facadeCarteraUsuaris.registre(nom, cognoms, nomUsuari, mail, pwd, pwd2, edat)
    }

    fun login(nomUsuari: String, pwd: String): Int {
        return facadeCarteraUsuaris.login(nomUsuari, pwd)
    }

    /**
     * RECEPTA *********************
     *         **********************
     */

    fun recorrerMenus(setmanaActual: String): ArrayList<String>? {
        // val usuari: Usuari
        //TODO: getUsuari a partir de nom
        //return usuari.recorrerMenus(setmanaActual)
        return null
    }

    fun setReceptaActiva(recepta: String?) {
        receptaActiva = recepta
    }

    fun getReceptaActiva(): String? {
        return receptaActiva
    }

    fun setReceptaFromProposta(titolRecepta: String) {
        isFromProposta = true
        titolReceptaProp = titolRecepta
    }

    fun setTitolReceptaFromCalendari(titol: String) {
        this.titolReceptaProp = titol
    }

    fun getIsFromProposta(): Boolean {
        return isFromProposta
    }

    fun setIsFromProposta(setRecepta: Boolean) {
        this.isFromProposta = setRecepta
    }

    //usuariActiu afegir dia, apat, setmana i titol
    fun setDiaRecepta(dia: String, apat: String, setmana: String, categoria: Int?) {
        facadeCarteraUsuaris.afegirInfoPlat(
            usuariActiu,
            dia,
            apat,
            setmana,
            titolReceptaProp,
            categoria
        )
        isFromProposta = false

    }

    fun getCategoriaApatDia(setmana: String, dia: String, apat: String): Int? {
        return facadeCarteraUsuaris.getCategoriaApatDia(usuariActiu, setmana, dia, apat)
    }

    fun afegirReceptaNova(
        nom: String, pasos: String, tempsPrep: String, tempsCuina: String,
        comensals: String, tipusRecepta: Int, ingredients: ArrayList<String>
    ): Int {
        if (nom.isEmpty() or pasos.isEmpty() or tempsPrep.isEmpty() or
            tempsCuina.isEmpty() or comensals.isEmpty() or ingredients.isNullOrEmpty()
        ) {
            return 1
        }
        if (facadeCarteraReceptes.addRecepta(
                nom, pasos, tempsPrep, tempsCuina, comensals, tipusRecepta,
                ingredients, usuariActiu!!
            )
        ) {
            return 0
        }
        return 2
    }

    fun getReceptaByName(nom: String): Proposta? {
        return facadeCarteraReceptes.getReceptaByName(nom)
    }

    fun getNumPropostes(): Int {
        return facadeCarteraReceptes.getNumPropostes()
    }

    fun afegirPropostaLayout(
        position: Int, imatge: ImageView, title: TextView, tempsP: TextView,
        tempsC: TextView, numPersones: TextView, icona: ImageView
    ) {
        imatge.setImageResource(facadeCarteraReceptes.getImage(position))
        title.text = facadeCarteraReceptes.getTitle(position)
        tempsP.text = facadeCarteraReceptes.getTPrep(position)
        tempsC.text = facadeCarteraReceptes.getTCuina(position)
        numPersones.text = facadeCarteraReceptes.getNPax(position)
        facadeCarteraReceptes.setIcona(icona, position)
    }

    fun getReceptaByPosition(position: Int): String? {
        return facadeCarteraReceptes.getTitle(position)
    }

    fun afegirReceptaLayout(
        titolRecepta: TextView, autor: TextView, passos: TextView,
        tPrep: TextView, tCuina: TextView, comensales: TextView, iconRecepta: ImageView
    ) {
        titolRecepta!!.text = receptaActiva
        val position = facadeCarteraReceptes.getPos(receptaActiva)
        autor.text = facadeCarteraReceptes.getAutor(position)
        passos.text = facadeCarteraReceptes.getDesc(position)
        tPrep.text = facadeCarteraReceptes.getTPrep(position)
        tCuina.text = facadeCarteraReceptes.getTCuina(position)
        comensales.text = facadeCarteraReceptes.getNPax(position)
        facadeCarteraReceptes.setIcona(iconRecepta, position)
    }

    fun getIconaReceptaActiva(): Int? {
        return facadeCarteraReceptes.getIcona(receptaActiva)
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

    fun getNumCuriositats(): Int {
        return facadeCarteraCuriositats.getNumCuriositats()
    }

    fun afegirCuriositatLayout(
        position: Int,
        imatge: ImageView,
        title: TextView,
        explicacio: TextView
    ) {
        imatge.setImageResource(facadeCarteraCuriositats.getImatge(position))
        title.text = facadeCarteraCuriositats.getTitle(position)
        explicacio.text = facadeCarteraCuriositats.getDescripcio(position)
    }

    /**
     * INGREDIENTS *********************
     *             **********************
     */

    fun getIngredientsByName(nomIngredient: String): Ingredient? {
        return facadeCarteraIngredients.getIngredientsByName(nomIngredient)
    }

    fun getNameIngredients(): ArrayList<String> {
        return facadeCarteraIngredients.getNameIngredients()
    }

    fun addNouIngredientAmbFoto(nomIngredient: String, fotoInt: Int) {
        facadeCarteraIngredients.addNouIngredientAmbFoto(nomIngredient, fotoInt)
    }

    fun addNouIngredientSenseFoto(nomIngredient: String) {
        facadeCarteraIngredients.addNouIngredientSenseFoto(nomIngredient)
    }

    fun getAllIngredientsByName(): ArrayList<String> {
        return facadeCarteraIngredients.getAllIngredientsByName()
    }

    fun afegirIngredientLlistaCompra(ingredient: String): Boolean {
        return facadeCarteraUsuaris.afegirIngredientLlistaCompra(getUsuariActiu(), ingredient)
    }

    fun treureIngredientLlistaCompra(ingredient: String): Boolean {
        return facadeCarteraUsuaris.treureIngredientLlistaCompra(getUsuariActiu(), ingredient)
    }


    fun getImatgeIngredient(nomIngredient: String): Int {
        return facadeCarteraIngredients.getImatgeIngredient(nomIngredient)
    }

    fun getLlistaIngredientsUsuari(): ArrayList<String>? {
        return facadeCarteraUsuaris.getLlistaUsuari(getUsuariActiu())
    }

    /**
     * FORUM *********************
     *      **********************
     */

    fun crearPregunta(descripcio: String, tema: String) {
        facadeCarteraPreguntes.crearPreguntaF(usuariActiu!!, descripcio, tema)
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<String>? {
        return facadeCarteraPreguntes.mostrarPreguntesPerTemaF(temaP)
    }

    fun crearResposta(
        tema: String,
        descripcio: String,
        esCertificat: Boolean,
        idUsuari: String,
        idDestinatari: String
    ) {
        facadeCarteraPreguntes.crearRespostaF(
            tema,
            descripcio,
            esCertificat,
            idUsuari,
            idDestinatari
        )
    }

    fun mostrarRespPerIdPreg(
        tema: String,
        descripcio: String,
        esCertificat: Boolean,
        idUsuari: String,
        idDestinatari: String
    ): ArrayList<Resposta>? {
        return facadeCarteraPreguntes.mostrarRespPerIdPregF(
            tema,
            descripcio,
            esCertificat,
            idUsuari,
            idDestinatari
        )
    }

    fun getContadorPreguntes(descripcio: String, tema: String): Int {
        return facadeCarteraPreguntes.getCont(usuariActiu!!, descripcio, tema)
    }

}