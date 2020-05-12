package com.example.govegan.model

class CarteraUsuaris(baseDades: BaseDades) {
    val llistaUsuaris: ArrayList<Usuari> = ArrayList()
    var baseDades: BaseDades

    init {
        llistaUsuaris.add(Usuari("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22))
        llistaUsuaris.add(Usuari("Clara", "Zabala", "czaba", "kkdlvkflk25", "claris99@gmail.com", 20))
        llistaUsuaris.add(Usuari("LLuis", "Roca", "lluis", "lluis", "lluis@gmail.com", 20))
        this.baseDades = baseDades
        this.baseDades.addUser("Dolores", "Tomacal", "dtomacal", "dtom97 ","dtomacal@gmail.com", 22)
        this.baseDades.addUser("Clara", "Zabala", "czaba", "kkdlvkflk25", "claris99@gmail.com", 20)
    }

    fun registre(nom: String, cognoms: String, nomUsuari: String, pwd: String, mail: String,
                 edat: String): Boolean {
        if (getByID(nomUsuari) != null){
            return false
        }
        if (pwd.length < 4){
        return false
        }
        baseDades.addUser(nom, cognoms, nomUsuari, pwd, mail, edat.toInt())
        val usuariNou = Usuari(nom, cognoms, nomUsuari, pwd, mail, edat.toInt())
        llistaUsuaris.add(usuariNou)
        return true
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

    fun getCategoriaApatDia(nomUsuari: String?, setmana: String, dia: String, apat: String): Int? {
        return getByID(nomUsuari!!)?.getCategoriaApatDia(setmana, dia, apat)
    }

    fun setRecepta(usuariActiu: String?, dia: String, apat: String, setmana: String, titol: String, categoria: Int?) {
        getByID(usuariActiu!!)?.setRecepta(dia,setmana,apat,titol,categoria)
    }

}



