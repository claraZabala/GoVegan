package com.example.govegan.model
import com.example.govegan.vista.Calendari_Setmanal
import com.example.govegan.vista.Recepta


class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int) {


    var nom:String
    var cognoms:String
    var nomUsuari:String
    var pwd:String
    var edat:Int
    var email:String
    lateinit var setmanes: ArrayList<Setmana>
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email
    }

    /*
    * Inicialitza l'atribut llistat de setmanes que conté tota la info de l'usuari
     */
    fun setSetmanesUsuari(setmanes: ArrayList<Setmana>) {
        this.setmanes = setmanes
    }

    /*
    * Desa un àpat provinent de recepta en el calendari personal
     */
    fun setRecepta(dia: String, apat: String, setmana: String, titolRecepta: String) {
        //recorrem les setmanes per a localitzar la cercada
        for (i in this.setmanes) {
            if (i.getName() == setmana) {
                //recorrem els dies d'aquesta setmana per localitzar el dia cercat
                for (j in i.dies) {
                    if (j.nom == dia) {
                        j.afegirApat(apat, titolRecepta)
                    }
                }
            }
        }
    }
}