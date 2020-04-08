package com.example.govegan.model

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreSettings
import java.util.concurrent.LinkedBlockingQueue
import java.util.concurrent.ThreadPoolExecutor
import java.util.concurrent.TimeUnit

class BaseDades(val db: FirebaseFirestore) {
    companion object {

        private val TAG = "DocSnippets"

        private val EXECUTOR = ThreadPoolExecutor(2, 4,
            60, TimeUnit.SECONDS, LinkedBlockingQueue()
        )
    }

    fun setup() {
        // [START get_firestore_instance]
        val db = FirebaseFirestore.getInstance()
        // [END get_firestore_instance]

        // [START set_firestore_settings]
        val settings = FirebaseFirestoreSettings.Builder()
            .setPersistenceEnabled(true)
            .build()
        db.firestoreSettings = settings
        // [END set_firestore_settings]
    }

    fun setupCacheSize() {
        // [START fs_setup_cache]
        val settings = FirebaseFirestoreSettings.Builder()
            .setCacheSizeBytes(FirebaseFirestoreSettings.CACHE_SIZE_UNLIMITED)
            .build()
        db.firestoreSettings = settings
        // [END fs_setup_cache]
    }

    fun addUser(nom: String, cognom: String, nomUsuari:String, pwd:String, email:String, edat: Int) {
        val user = hashMapOf(
            "nom" to nom,
            "cognom" to cognom,
            "nomUsuari" to nomUsuari,
            "pwd" to pwd,
            "email" to email,
            "edat" to edat
        )

        // Add a new document with a generated ID
        db.collection("users")
            .add(user)
            .addOnSuccessListener { documentReference ->
                Log.d(TAG, "DocumentSnapshot added with ID: ${documentReference.id}")
            }
            .addOnFailureListener { e ->
                Log.w(TAG, "Error adding document", e)
            }

        db.collection("users").document(nomUsuari).set(user)
        // [END add_ada_lovelace]
    }

    fun getAllUsers() {
        // [START get_all_users]
        db.collection("users")
            .get()
            .addOnSuccessListener { result ->
                for (document in result) {
                    Log.d(TAG, "${document.id} => ${document.data}")
                }
            }
            .addOnFailureListener { exception ->
                Log.w(TAG, "Error getting documents.", exception)
            }
        // [END get_all_users]
    }

    fun getUserByID(nomUsuari: String): String{
        var mess: String = ""
        val docRef = db.collection("users").document("dtomacal")
        docRef.get()
            .addOnSuccessListener { document ->
                if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    mess = "DocumentSnapshot data: ${document.data}"
                } else {
                    Log.d(TAG, "No such document")
                    mess = "No such document"
                }
            }
            .addOnFailureListener { exception ->
                Log.d(TAG, "get failed with ", exception)
            }
        return mess
    }
}