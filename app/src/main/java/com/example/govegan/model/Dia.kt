package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {


    private val idSetmana: Int
    var nom: String = nom
    private var esmorzar: MutableMap<String?, String>
    private var dinar: MutableMap<String?, String>
    private var sopar: MutableMap<String?, String>


    init {
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
        this.dinar = mutableMapOf()
        this.sopar = mutableMapOf()
    }
    constructor():this(0,"")

    /*
    * Afegeix als Diccionaris el titol del plat junt amb un Int
    * vegà ---> 0
    * vegetarià ---> 1
    * carn ---> 2
     */
    fun afegirApat(apat: String, titolRecepta: String,categoria:String?) {
        when (apat) {
            "esmorzar" -> this.esmorzar[categoria] = titolRecepta
            "dinar" -> this.dinar[categoria] = titolRecepta
            "sopar" -> this.sopar[categoria] = titolRecepta
            else -> print("caca")
        }

    }

    fun getApat(apat:String):String?{
        var tipus:String? = null
        when (apat) {
            "esmorzar" ->{for (i in esmorzar){
                tipus = if(i.key?.equals("2")!!){
                    return "2"
                } else if(i.key?.equals("1")!!){
                    "1"
                } else{
                    "0"
                }

            }
            }

            "dinar" ->{for (i in dinar){
                tipus = if(i.key?.equals("2")!!){
                    return "2"
                } else if(i.key?.equals("1")!!){
                    "1"
                } else{
                    "0"
                }

            }
            }
            "sopar" ->{for (i in sopar){
                tipus = if(i.key?.equals("2")!!){
                    return "2"
                } else if(i.key?.equals("1")!!){
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
