package com.example.govegan.model

class Dia(idSetmana: Int, nom: String) {


    val idSetmana: Int
    var nom: String
    var esmorzar: MutableMap<Int?, String>
    var dinar: MutableMap<Int?, String>
    var sopar: MutableMap<Int?, String>


    init {
        this.nom = nom
        this.idSetmana = idSetmana
        this.esmorzar = mutableMapOf()
        this.dinar = mutableMapOf()
        this.sopar = mutableMapOf()
    }

    /*
    * Afegeix als Diccionaris el titol del plat junt amb un Int
    * vegà ---> 0
    * vegetarià ---> 1
    * carn ---> 2
     */
    fun afegirApat(apat: String, titolRecepta: String,categoria:Int?) {
        when (apat) {
            "esmorzar" -> this.esmorzar.put(categoria, titolRecepta)
            "dinar" -> this.dinar.put(categoria, titolRecepta)
            "sopar" -> this.sopar.put(categoria, titolRecepta)
            else -> print("caca")
        }

    }
    fun getApat(apat:String):Int?{
        var tipus:Int? = null
        when (apat) {
            "esmorzar" ->{for (i in esmorzar){
                if(i.key?.equals(2)!!){
                    return 2
                }
                else if(i.key?.equals(1)!!){
                    tipus = 1
                }
                else{
                    tipus = 0
                }

            }
            }

            "dinar" ->{for (i in dinar){
                if(i.key?.equals(2)!!){
                    return 2
                }
                else if(i.key?.equals(1)!!){
                    tipus = 1
                }
                else{
                    tipus = 0
                }

            }
            }
            "sopar" ->{for (i in sopar){
                if(i.key?.equals(2)!!){
                    return 2
                }
                else if(i.key?.equals(1)!!){
                    tipus = 1
                }
                else{
                    tipus = 0
                }

            }
            }


        }
        return tipus


    }

}