package com.example.govegan.model

import android.util.Log
import com.example.govegan.controlador.Controlador
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BaseDades(val db: FirebaseFirestore) {
    var controlador:Controlador = Controlador
    var userID:String = ""
    companion object {
        private val TAG = "DocSnippets"

    }
    fun actualitzarUsuariActiu(){
        //TODO: Arreglar això ja que ara usuariActiu es String
        var usuari:String? = controlador.getUsuariActiu()
        if(usuari != null) {
            db.collection("users").document(userID).set(usuari)
        }
    }
    fun getUsuariActiu(ID:String){
        var usuari:Usuari?
        val docRef = db.collection("users").document(ID)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            usuari = documentSnapshot.toObject(Usuari::class.java)
            controlador.setUsuariActiu(usuari?.nomUsuari)
        }
        userID = ID
    }

    fun addUser(usuari:Usuari,userID:String) {
        db.collection("users").document(userID).set(usuari)
    }

    fun userExists(nomUsuari: String): Boolean {
        var exists = true
        val docRef = db.collection("users").document(nomUsuari)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    //print("""${document.data}""")
                    exists = true
                } else {
                    Log.d(TAG, "No such document")
                    exists = false
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return exists
    }

    fun getAllUsers(): ArrayList<String> {
        // [START get_all_users]
        val users : ArrayList<String> = ArrayList()
        val returnUsers = db.collection("users").get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    users.add("DocumentSnapshot data: ${document.data}")
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        return users
        // [END get_all_users]
    }

    /*
    * Es cerquen totes les setmanes corresponets a l'usuari en la base de dades
     */
    fun inicialitzarSetmanesUsuari(nomUsuari: String): ArrayList<Setmana> {
        val setmanesUsuari: ArrayList<Setmana> = ArrayList()
        /*
        setmanes_usuari = getAllSetmanes()
        for i in setmanes_usuari comparar idUsuari
        si coincideix --> setmanes_usuari.add(i)
         */
        return setmanesUsuari
    }
}