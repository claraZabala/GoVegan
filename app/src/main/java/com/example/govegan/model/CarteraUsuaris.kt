package com.example.govegan.model
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class CarteraUsuaris(baseDades: BaseDades) {
    val llistaUsuaris: ArrayList<Usuari> = ArrayList()
    lateinit var db:FirebaseFirestore

    init {

        llistaUsuaris.add(Usuari("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22,ArrayList(),
            ArrayList()
        ))
        llistaUsuaris.add(Usuari("Clara", "Zabala", "czaba", "kkdlvkflk25", "claris99@gmail.com", 20,ArrayList(),ArrayList()))
        llistaUsuaris.add(Usuari("LLuis", "Roca", "lluis", "lluis", "lluis@gmail.com", 20,ArrayList(),ArrayList()))


    }
    fun carregarUsuari(usuari: Usuari?){
        if(usuari != null && usuari !in llistaUsuaris){
            llistaUsuaris.add(usuari)
        }
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, pwd: String, mail: String,
                 edat: String): Usuari? {
        if (getByID(nomUsuari) != null){
            return null
        }

        val usuariNou = Usuari(nom, cognoms, nomUsuari, pwd, mail, edat.toInt(),ArrayList(),
            ArrayList()
        )

        llistaUsuaris.add(usuariNou)
        return usuariNou


    }

    fun login(nomUsuari: String, pwd: String): Boolean {
        val user = getByID(nomUsuari)
        if (user == null) {
            return false
        } else if(!user.pwd.equals(pwd)){
            return false
        }
        return true
    }

    fun getByID(nomUsuari: String): Usuari? {
        for (item in llistaUsuaris) {
            if (item.nomUsuari.equals(nomUsuari)){
                return item
            }
        }
        return null
    }

    /*
    S'inicialitza la informació de l'usuari passsat per paràmetre
     */
    fun setUsuariActiu(nomUsuari: String) {
        val usuari: Usuari? = getByID(nomUsuari)
        val setmanes: ArrayList<Setmana> = usuari?.setmanes!!
        if (usuari != null) {
            usuari.setSetmanesUsuari(setmanes)
        }
    }


    fun afegirIngredientLlistaCompra(nomUsuari: String?,ingredient: String): Boolean {
        for (item in llistaUsuaris) {
            if (item.nomUsuari.equals(nomUsuari)){
                item.llistaIngredientsCompra.add(ingredient)
                return true
            }
        }
        return false
    }

    fun treureIngredientLlistaCompra(nomUsuari: String?,ingredient: String):Boolean{
        for (item in llistaUsuaris) {
            if (item.nomUsuari.equals(nomUsuari)){
                if(ingredient in item.llistaIngredientsCompra) {
                    item.llistaIngredientsCompra.remove(ingredient)
                    return true
                }
            }
        }
        return false
    }

    fun getLlistaUsuari(nomUsuari: String?):ArrayList<String>?{
        for (item in llistaUsuaris) {
            if (item.nomUsuari.equals(nomUsuari)) {
                return item.llistaIngredientsCompra
            }
        }

    return null
    }


    fun initUsers(allUsers: ArrayList<String>) {
        print(allUsers.toString())
    }

    fun getCategoriaApatDia(nomUsuari: String?, setmana: String, dia: String, apat: String):String? {
        return getByID(nomUsuari!!)?.getCategoriaApatDia(setmana, dia, apat)
    }

    fun setRecepta(usuariActiu: String?, dia: String, apat: String, setmana: String, titol: String, categoria: String?) {
        getByID(usuariActiu!!)?.setRecepta(dia,setmana,apat,titol,categoria)
    }

}



