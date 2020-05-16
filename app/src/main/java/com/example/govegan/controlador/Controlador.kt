package com.example.govegan.controlador

import android.content.Context
import android.os.Build
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.example.govegan.model.*
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDate
import java.time.temporal.WeekFields
import java.util.*
import kotlin.collections.ArrayList

object Controlador {
    private var facadeCarteraCuriositats: FacadeCarteraCuriositats
    private var facadeCarteraIngredients: FacadeCarteraIngredients
    private var facadeCarteraPreguntes: FacadeCarteraPreguntes
    private var facadeCarteraUsuaris: FacadeCarteraUsuaris
    private var facadeCarteraReceptes: FacadeCarteraReceptes
    private var usuariActiu: String?
    private var correuUsuariActiu: String?
    private var receptaActiva: String?
    private var isFromProposta: Boolean
    private var titolReceptaProp: String
    private val baseDades: BaseDades
    private val db: FirebaseFirestore = FirebaseFirestore.getInstance()


    /**
     * INITS *********************
     *      **********************
     */

    init {
        baseDades = BaseDades(db)
        facadeCarteraCuriositats = FacadeCarteraCuriositats(baseDades)
        facadeCarteraIngredients = FacadeCarteraIngredients(baseDades)
        facadeCarteraUsuaris = FacadeCarteraUsuaris(baseDades)
        facadeCarteraPreguntes = FacadeCarteraPreguntes(baseDades)
        facadeCarteraReceptes = FacadeCarteraReceptes(baseDades)
        usuariActiu = null
        receptaActiva = null
        isFromProposta = false
        correuUsuariActiu = null
        titolReceptaProp = ""
        facadeCarteraIngredients.getLlistaBaseDades()
        facadeCarteraReceptes.getLlistaBaseDades()
        facadeCarteraCuriositats.getLlistaBaseDades()
        facadeCarteraPreguntes.getLListaBaseDades()
        //initPreguntesBD()
    }

    /*private fun initCuriositatsBD() {
        for (curiositat in facadeCarteraCuriositats.getLlistaCuriositats()) {
            baseDades.addCuriositat(curiositat)
        }
    }*/

    private fun initPreguntesBD() {
        for (pregunta in facadeCarteraPreguntes.getLlistaPreguntes()!!) {
            baseDades.addPregunta(pregunta)
        }
    }

    fun getCorreuUsuariActiu():String?{
        return correuUsuariActiu
    }
    private fun actualizarUsuariActiu(){
        baseDades.actualitzarUsuariActiu()

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

    fun setUsuariActiu(usuari: Usuari?) {
            usuariActiu = usuari?.nomUsuari
            facadeCarteraUsuaris.carregarUsuari(usuari)
        }


    fun registre(userID:String?, nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String, weekNumber: Int): Int {
        return facadeCarteraUsuaris.registre(userID,nom, cognoms, nomUsuari, mail, pwd, pwd2, edat, weekNumber)
    }

    fun getUsuariByName (name: String): Usuari? {
        return facadeCarteraUsuaris.getUsuariByName(name)
    }

    fun login(ID:String) {
        return facadeCarteraUsuaris.login(ID)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getSetmanaActual(): Int {
        val setInicial = facadeCarteraUsuaris.getSetmanaUser(usuariActiu)
        val weekNumber = LocalDate.now().get(WeekFields.of(Locale.getDefault()).weekOfWeekBasedYear())
        return ((weekNumber- setInicial!!).rem(52)) + 1 //(actual-inicial)mod52 + 1
    }

    /*fun recuperarContra(correu: String,context: Context) {
       baseDades.recuperarContra(correu,context)
    }*/

    /**
     * RECEPTA *********************
     *         **********************
     */

    fun recorrerMenus(setmanaActual: Int): ArrayList<String>? {
        val usuari: Usuari? = usuariActiu?.let { getUsuariByName(it) }
        return if (usuari != null) {
            usuari.recorrerMenus(setmanaActual)
        } else null
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
    fun setDiaRecepta(dia: String, apat: String, setmana: String,categoria:String?): String? {
        isFromProposta = false
        actualizarUsuariActiu()
        return facadeCarteraUsuaris.afegirInfoPlat(usuariActiu, dia, apat, setmana, titolReceptaProp,categoria)
    }

    fun getCategoriaApatDia(setmana:String,dia:String,apat:String):String?{
        if(usuariActiu != null) {
            return facadeCarteraUsuaris.getUsuariByName(usuariActiu!!)
                ?.getCategoriaApatDia(setmana, dia, apat)
        }
        return null
    }

    fun afegirReceptaNova(lastpath:String?,nom: String, pasos: String, tempsPrep: String, tempsCuina: String,
                          comensals:String, tipusRecepta:String, ingredients: ArrayList<String>): Int {
        if (nom.isEmpty() or pasos.isEmpty() or tempsPrep.isEmpty() or
            tempsCuina.isEmpty() or comensals.isEmpty() or ingredients.isNullOrEmpty()){
            return 1
        }
        val proposta = facadeCarteraReceptes.addRecepta(lastpath,nom,pasos,tempsPrep,tempsCuina,comensals,tipusRecepta,ingredients, usuariActiu!!)
        if (proposta!=null){
            baseDades.addProposta(proposta)
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
    fun carregarImatgeIngredient(context: Context,imatge: ImageView,ingredient: String){
        baseDades.carregarImatgeIngredient(context,imatge, facadeCarteraIngredients.getImatgeIngredient(ingredient))

    }
    fun afegirPropostaLayout(context: Context,
        position: Int, imatge: ImageView, title: TextView, tempsP: TextView,
        tempsC: TextView, numPersones: TextView, icona: ImageView
    ) {
        baseDades.carregarImatgeRecepta(context,imatge,facadeCarteraReceptes.getPath(position))
        title.text = facadeCarteraReceptes.getTitle(position)
        tempsP.text = facadeCarteraReceptes.getTPrep(position)
        tempsC.text = facadeCarteraReceptes.getTCuina(position)
        numPersones.text = facadeCarteraReceptes.getNPax(position)
        facadeCarteraReceptes.setIcona(icona, position)
    }

    fun getReceptaByPosition(position: Int): String? {
        return facadeCarteraReceptes.getTitle(position)
    }

    fun afegirReceptaLayout(context: Context,
        titolRecepta: TextView, autor: TextView, passos: TextView,
        tPrep: TextView, tCuina: TextView, comensales: TextView, iconRecepta: ImageView
    ) {
        titolRecepta.text = receptaActiva
        val position = facadeCarteraReceptes.getPos(receptaActiva)
        autor.text = facadeCarteraReceptes.getAutor(position)
        passos.text = facadeCarteraReceptes.getDesc(position)
        tPrep.text = facadeCarteraReceptes.getTPrep(position)
        tCuina.text = facadeCarteraReceptes.getTCuina(position)
        comensales.text = facadeCarteraReceptes.getNPax(position)
        baseDades.carregarImatgeRecepta(context,iconRecepta,facadeCarteraReceptes.getPath(position))
    }

    fun getIconaReceptaActiva(): String? {
        return facadeCarteraReceptes.getIcona(receptaActiva)
    }

    fun getIngredientsProp(): ArrayList<String> {
        return facadeCarteraReceptes.getIngredients(receptaActiva)
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

    fun addCuriositat(tema: String, desc: String, imatge: String?): Boolean {
        val curiositat = facadeCarteraCuriositats.addCuriositat(tema, desc, imatge)
        if (curiositat!=null){
            baseDades.addCuriositat(curiositat)
            return true
        }
        return false
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

    fun afegirCuriositatLayout(context: Context,
        position: Int,
        imatge: ImageView,
        title: TextView,
        explicacio: TextView
    ) {
        baseDades.carregarImatgeCuriositat(context,imatge, facadeCarteraCuriositats.getImatge(position))
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

    fun addNouIngredientAmbFoto(nomIngredient: String, fotoInt: String?) {
        facadeCarteraIngredients.addNouIngredientAmbFoto(nomIngredient, fotoInt)
    }

    fun addNouIngredientSenseFoto(nomIngredient: String) {
        facadeCarteraIngredients.addNouIngredientSenseFoto(nomIngredient)
    }

    fun getAllIngredientsByName(): ArrayList<String> {
        return facadeCarteraIngredients.getAllIngredientsByName()
    }

    fun afegirIngredientLlistaCompra(ingredient: String): Boolean {
        if(facadeCarteraUsuaris.afegirIngredientLlistaCompra(usuariActiu,ingredient)) {
            actualizarUsuariActiu()
            return true
        }
        return false
    }

    fun treureIngredientLlistaCompra(ingredient: String):Boolean{
        facadeCarteraUsuaris.treureIngredientLlistaCompra(getUsuariActiu(), ingredient)
        actualizarUsuariActiu()
            return true
    }

    fun getImatgeIngredient(nomIngredient: String): String? {
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
        val pregunta:Pregunta = facadeCarteraPreguntes.crearPreguntaF(usuariActiu!!, descripcio, tema)
        baseDades.addPregunta(pregunta)
    }

    fun mostrarPreguntesPerTema(temaP: String): ArrayList<String>? {
        return facadeCarteraPreguntes.mostrarPreguntesPerTemaF(temaP)
    }

    fun crearResposta(
        tema: String,
        descripcio: String,
        esCertificat: Boolean,
        idUsuari: String,
        idDestinatari: String,
        descPreg : String
    ) {
        val preg = facadeCarteraPreguntes.crearRespostaF(
            tema,
            descripcio,
            esCertificat,
            idUsuari,
            idDestinatari,
            descPreg
        )
        if (preg != null) {
            baseDades.addPregunta(preg)
        }
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

    fun getUsuari(descripcio: String): String {
        return facadeCarteraPreguntes.getUsari(descripcio)
    }
  
    fun mostrarRespostesPerDesc( idUsuari: String, desc : String, tema: String): ArrayList<String>?{
        return facadeCarteraPreguntes.mostrarRespostesPerDesc( idUsuari, desc , tema)
    }
}