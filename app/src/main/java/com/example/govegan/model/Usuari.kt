package com.example.govegan.model


class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int,
             setmanes: ArrayList<Setmana>,llistaIngredientsCompra:ArrayList<String>, weekNumber: Int) {



    var nom:String
    var cognoms:String
    var nomUsuari:String
    var pwd:String
    var edat:Int
    var email:String
    var setmanes: ArrayList<Setmana>
    var llistaIngredientsCompra:ArrayList<String>
    var weekNumber: Int


    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email
        this.setmanes = setmanes
        this.llistaIngredientsCompra = llistaIngredientsCompra
        this.weekNumber = weekNumber
        iniSetmanes()
    }

    /*
    * Inicialitza l'atribut llistat de setmanes que conté tota la info de l'usuari
     */
    constructor():this("","","","","",0, ArrayList(),ArrayList(),0){

    }
    fun iniSetmanes(){
        for (i in 1..24) {
            setmanes.add(Setmana(nomUsuari, i))
        }
    }
    fun setSetmanesUsuari(setmanes: ArrayList<Setmana>) {
        this.setmanes = setmanes
    }

    /*
    * Desa un àpat provinent de recepta en el calendari personal
     */
    fun setRecepta(dia: String, apat: String, setmana: String, titolRecepta: String,categoria:String?): String? {
        var retorn: String? = null
        //recorrem les setmanes per a localitzar la cercada
        for (i in this.setmanes) {
            if (i.getName().equals(setmana)) {
                //recorrem els dies d'aquesta setmana per localitzar el dia cercat
                for (j in i.dies) {
                    if (j.nom.equals(dia)) {
                        retorn = j.afegirApat(apat, titolRecepta,categoria)
                    }
                }
            }
        }
        return retorn
    }

    fun recorrerMenus(setmanaActual: Int): ArrayList<String> {
        var llistaMenus: ArrayList<String> = ArrayList()
        for (i in setmanes) {
            if (i.getName().equals("Setmana "+setmanaActual)) {
                llistaMenus = i.recorrerDies()
            }
        }
        return llistaMenus
    }
    //Aquesta funció retorna el tipus d'apat que és, si vegà, vegetaria o amb carn
    fun getCategoriaApatDia(setmana:String,dia:String,apat:String):String?{
        for (i in setmanes){
            if(i.getName().equals(setmana))
                for (j in i.dies) {
                    if (j.nom.equals(dia)) {
                        return j.getApat(apat)
                    }
                }
        }
        return null
    }
}