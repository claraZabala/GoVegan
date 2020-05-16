package com.example.govegan.controlador

import com.example.govegan.model.BaseDades
import com.example.govegan.model.CarteraUsuaris
import com.example.govegan.model.Usuari
import com.google.firebase.firestore.FirebaseFirestore

class FacadeCarteraUsuaris (baseDades: BaseDades) {
    var carteraUsuaris: CarteraUsuaris
    var controlador: Controlador
    var baseDades: BaseDades

    init{
        carteraUsuaris = CarteraUsuaris(baseDades)
        controlador = Controlador
        this.baseDades = baseDades
    }
    fun carregarUsuari(usuari: Usuari?){
        carteraUsuaris.carregarUsuari(usuari)
    }

    fun login(ID:String){

        baseDades.getUsuariActiu(ID)

    }


    fun registre(userID:String?,nom: String, cognoms: String, nomUsuari: String, mail: String, pwd: String,
                 pwd2: String, edat: String, weekNumber: Int): Int {
        if (pwd.isBlank() or pwd2.isBlank() or mail.isBlank() or nom.isBlank()
            or edat.isBlank() or cognoms.isBlank() or nomUsuari.isBlank()) {
            return 1
        } else if (!pwd.equals(pwd2)) {
            return 2
        }
        else if (userID == null){
            return 4
        }
        else if(pwd.length < 6){
            return 5
        }

        else {
            var usuariNou = carteraUsuaris.registre(nom, cognoms, nomUsuari, pwd, mail, edat, weekNumber)
            if (usuariNou != null) {
                if(userID != null) {
                    baseDades.addUser(usuariNou,userID)
                }
                return 0
            } else {
                return 3
            }
        }
    }
    fun afegirIngredientLlistaCompra(nomUsuari: String?,ingredient: String): Boolean {
        return carteraUsuaris.afegirIngredientLlistaCompra(nomUsuari,ingredient)
    }
    fun treureIngredientLlistaCompra(nomUsuari: String?,ingredient: String):Boolean{
        return carteraUsuaris.treureIngredientLlistaCompra(nomUsuari,ingredient)
    }

    fun getLlistaUsuari(nomUsuari: String?):ArrayList<String>? {
        return carteraUsuaris.getLlistaUsuari(nomUsuari)
    }

    fun afegirInfoPlat(
        usuariActiu: String?,
        dia: String,
        apat: String,
        setmana: String,
        titol: String,
        categoria:String?
    ): String? {
        return carteraUsuaris.setRecepta(usuariActiu,dia, apat, setmana, titol,categoria)
    }

    fun initUsers(allUsers: ArrayList<String>) {
        carteraUsuaris.initUsers(allUsers)
    }

    fun getCategoriaApatDia(usuariActiu: String?, setmana: String, dia: String, apat: String): String? {
        return carteraUsuaris.getCategoriaApatDia(usuariActiu,setmana,dia,apat)
    }

    fun getUsuariByName(name: String): Usuari? {
        return carteraUsuaris.getByID(name)
    }

    fun getSetmanaUser(usuariActiu: String?): Int? {
        return carteraUsuaris.getSetmanaUser(usuariActiu)
    }


}