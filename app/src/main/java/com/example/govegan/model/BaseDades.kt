package com.example.govegan.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BaseDades(val db: FirebaseFirestore) {
    companion object {

        private val TAG = "DocSnippets"

    }

    fun addUser(nom: String, cognom: String, nomUsuari:String, pwd:String, email:String, edat: Int) {
        val users = db.collection("users")
        if (!userExists(nomUsuari)){
            val user = hashMapOf(
                "nom" to nom,
                "cognom" to cognom,
                "nomUsuari" to nomUsuari,
                "pwd" to pwd,
                "email" to email,
                "edat" to edat
            )
            users.document(nomUsuari).set(user)
        }
    }

    fun userExists(nomUsuari: String): Boolean {
        var exists = true
        val docRef = db.collection("users").document(nomUsuari)
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
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
                    users.add(document.data.values.toString())
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