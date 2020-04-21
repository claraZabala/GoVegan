package com.example.govegan.controlador

import com.example.govegan.model.*
import com.example.govegan.vista.Forum
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage

object Controlador {
    private var facadeCarteraCuriositats: FacadeCarteraCuriositats
    private var façanaCarteraIngredients:FaçanaCarteraIngredients
    private var facadeCarteraPreguntes:FacadeCarteraPreguntes
    private var carteraUsuaris: CarteraUsuaris
    private var usuariActiu: Usuari?
    private var setRecepta: Boolean
    private var titolReceptaProp: String

    init {
        facadeCarteraCuriositats = FacadeCarteraCuriositats()
        façanaCarteraIngredients = FaçanaCarteraIngredients()
        carteraUsuaris = CarteraUsuaris()
        facadeCarteraPreguntes = FacadeCarteraPreguntes()
        usuariActiu = null
        setRecepta = false
        titolReceptaProp = ""
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
        pwd2: String, edat: String): Int {
        if (pwd.isNullOrBlank() or pwd2.isNullOrBlank() or mail.isNullOrBlank() or nom.isNullOrBlank()
            or edat.isNullOrBlank() or cognoms.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        } else if (!pwd.equals(pwd2)) {
            return 2
        } else {
            if (carteraUsuaris.registre(nom, cognoms, nomUsuari, pwd, mail, edat)) {
                return 0
            } else {
                return 3
            }
        }
    }

    fun login(nomUsuari: String, pwd: String): Int {
        if (pwd.isNullOrBlank() or nomUsuari.isNullOrBlank()) {
            return 1
        } else {
            if (carteraUsuaris.login(nomUsuari, pwd)) {
                //es porta a memòria la info de l'usuari (les seves setmanes)
                usuariActiu = carteraUsuaris.setUsuariActiu(nomUsuari)
                if (usuariActiu == null) println("Usuari null")
                return 0
            }
            else{
                return 2
            }
        }
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
        return façanaCarteraIngredients.getIngredientsByName(nomIngredient)
    }

    fun getNameIngredients():ArrayList<String>{
        return façanaCarteraIngredients.getNameIngredients()
    }

    fun addNouIngredientAmbFoto(nomIngredient: String,fotoInt: Int){
        façanaCarteraIngredients.addNouIngredientAmbFoto(nomIngredient,fotoInt)
    }

    fun addNouIngredientSenseFoto(nomIngredient: String){
        façanaCarteraIngredients.addNouIngredientSenseFoto(nomIngredient)
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

    }
}