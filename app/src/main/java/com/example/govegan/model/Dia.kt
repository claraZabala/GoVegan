package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {


    val idSetmana: Int
    var nom: String
    var esmorzar: MutableMap<String?, String>
    var dinar: MutableMap<String?, String>
    var sopar: MutableMap<String?, String>


    init {
        this.nom = nom
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
        this.dinar = mutableMapOf()
        this.sopar = mutableMapOf()
    }
    constructor():this(0,""){

    }

    /*
    * Afegeix als Diccionaris el titol del plat junt amb un Int
    * vegà ---> 0
    * vegetarià ---> 1
    * carn ---> 2
     */
    fun afegirApat(apat: String, titolRecepta: String,categoria:String?) {
        when (apat) {
            "esmorzar" -> this.esmorzar.put(categoria, titolRecepta)
            "dinar" -> this.dinar.put(categoria, titolRecepta)
            "sopar" -> this.sopar.put(categoria, titolRecepta)
            else -> print("caca")
        }

    }

    fun getApat(apat:String):String?{
        var tipus:String? = null
        when (apat) {
            "esmorzar" ->{for (i in esmorzar){
                if(i.key?.equals("2")!!){
                    return "2"
                }
                else if(i.key?.equals("1")!!){
                    tipus = "1"
                }
                else{
                    tipus = "0"
                }

            }
            }

            "dinar" ->{for (i in dinar){
                if(i.key?.equals("2")!!){
                    return "2"
                }
                else if(i.key?.equals("1")!!){
                    tipus = "1"
                }
                else{
                    tipus ="0"
                }

            }
            }
            "sopar" ->{for (i in sopar){
                if(i.key?.equals("2")!!){
                    return "2"
                }
                else if(i.key?.equals("1")!!){
                    tipus = "1"
                }
                else{
                    tipus ="0"
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
            llistatApats.add(i.value)
        }
        llistatApats.add("-1")
        //Es recorre dinar
        for (i in dinar) {
            llistatApats.add(i.value)
        }
        llistatApats.add("-1")
        //es recorre sopar
        for (i in sopar) {
            llistatApats.add(i.value)
        }

        return llistatApats
    }

}
