package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {
    val idSetmana: Int
    var nom: String = nom
    var esmorzar: ArrayList<String?>
    var dinar: ArrayList<String?>
    var sopar: ArrayList<String?>

    init {
        this.idSetmana = idSetmana
        this.esmorzar = arrayListOf()
        this.dinar = arrayListOf()
        this.sopar = arrayListOf()
    }
    constructor():this(0,"")

    /*
    * Afegeix als Diccionaris el titol del plat junt amb un Int
    * vegà ---> 0
    * vegetarià ---> 1
    * carn ---> 2
     */
    fun afegirApat(apat: String, titolRecepta: String,categoria:String?): String? {
        when (apat) {
            "esmorzar" -> {
                this.esmorzar.add(categoria + titolRecepta)
                return getApat("esmorzar")
            }
            "dinar" -> {
                this.dinar.add(categoria + titolRecepta)
                return getApat("dinar")
            }
            "sopar" -> {
                this.sopar.add(categoria + titolRecepta)
                return getApat("sopar")
            }
            else -> {
                print("caca")
                return null
            }
        }
    }

    fun getApat(apat:String):String?{
        var tipus:String? = null
        when (apat) {
            "esmorzar" ->{for (i in esmorzar){
                tipus = if(i?.get(0)?.equals("2")!!){
                    return "2"
                } else if(i?.get(0)?.equals("1")!!){
                    "1"
                } else{
                    "0"
                }
            }
            }
            "dinar" ->{for (i in dinar){
                tipus = if(i?.get(0)?.equals("2")!!){
                    return "2"
                } else if(i?.get(0)?.equals("1")!!){
                    "1"
                } else{
                    "0"
                }
            }
            }
            "sopar" ->{for (i in sopar){
                tipus = if(i?.get(0)?.equals("2")!!){
                    return "2"
                } else if(i?.get(0)?.equals("1")!!){
                    "1"
                } else{
                    "0"
                }
            }
            }
        }
        return tipus
    }

    fun recorrerApats(): ArrayList<String>{
        val llistatApats: ArrayList<String> = ArrayList()
        //es recorre esmorzar
        for (i in esmorzar) {
            llistatApats.add(i!!.substring(1))
        }
        llistatApats.add("-1")
        //Es recorre dinar
        for (i in dinar) {
            llistatApats.add(i!!.substring(1))
        }
        llistatApats.add("-1")
        //es recorre sopar
        for (i in sopar) {
            llistatApats.add(i!!.substring(1))
        }
        return llistatApats
    }
}
