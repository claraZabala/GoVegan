package com.example.govegan.model


class Usuari(nom:String, cognoms:String, nomUsuari:String, pwd:String, email:String, edat:Int) {


    var nom:String
    var cognoms:String
    var nomUsuari:String
    var pwd:String
    var edat:Int
    var email:String
    var setmanes: ArrayList<Setmana> = ArrayList()
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()

    init {
        this.nom = nom
        this.cognoms = cognoms
        this.edat = edat
        this.nomUsuari = nomUsuari
        this.pwd = pwd
        this.email = email
        for (i in 1..24)
        setmanes.add(Setmana(this,i))
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
    fun setRecepta(dia: String, apat: String, setmana: String, titolRecepta: String,categoria:Int?) {
        //recorrem les setmanes per a localitzar la cercada
        for (i in this.setmanes) {
            if (i.getName().equals(setmana)) {
                //recorrem els dies d'aquesta setmana per localitzar el dia cercat
                for (j in i.dies) {
                    if (j.nom.equals(dia)) {
                        j.afegirApat(apat, titolRecepta,categoria)
                    }
                }
            }
        }
    }
    //Aquesta funció retorna el tipus d'apat que és, si vegà, vegetaria o amb carn
    fun getCategoriaApatDia(setmana:String,dia:String,apat:String):Int?{
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