package com.example.govegan.model

import android.content.Context
import android.net.Uri
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.govegan.controlador.Controlador
import com.google.android.gms.tasks.OnSuccessListener
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.storage.FirebaseStorage

class BaseDades(val db: FirebaseFirestore) {
    var controlador:Controlador = Controlador
    var userID:String = ""
    var mStorage = FirebaseStorage.getInstance().getReference()

    companion object {
        private val TAG = "DocSnippets"
    }
    fun carregarImatge(context: Context,imatge:ImageView,lastPath:String?){
        if(lastPath != null) {
            var storageRef =
            FirebaseStorage.getInstance().getReference();
            storageRef.child("fotosRecepta").child(lastPath).getDownloadUrl()
                .addOnSuccessListener {
                    if(it != null){
                        Glide.with(context)
                            .load(it)
                            .fitCenter()
                            .centerCrop()
                            .into(imatge)
                    }
                    }
        }
    }
    fun actualitzarUsuariActiu() {
        var usuari: Usuari? = controlador.getUsuariByName(controlador.getUsuariActiu()!!)
        if (usuari != null) {
            db.collection("users").document(userID).set(usuari)
        }
    }
    fun getAllIngredients():ArrayList<Ingredient>{
        var ingredients:ArrayList<Ingredient> = ArrayList()
        db.collection("ingredients").get().addOnSuccessListener {
            resultat->
            for(ingredient: QueryDocumentSnapshot in resultat){
                ingredients.add(ingredient.toObject(Ingredient::class.java))
            }
        }
        return ingredients

    }

    fun getAllPropostes():ArrayList<Proposta>{
        var propostes:ArrayList<Proposta> = ArrayList()
        db.collection("propostes").get().addOnSuccessListener {
                resultat->
            for(ingredient: QueryDocumentSnapshot in resultat){
                propostes.add(ingredient.toObject(Proposta::class.java))
            }
        }
        return propostes
    }
    fun addIngredients(ingredient: Ingredient){
        db.collection("ingredients").document(ingredient.nom).set(ingredient)
    }

    fun addProposta(proposta: Proposta){
        db.collection("propostes").document(proposta.title).set(proposta)
    }



    fun getUsuariActiu(ID:String){
        var usuari:Usuari?
        val docRef = db.collection("users").document(ID)
        docRef.get().addOnSuccessListener { documentSnapshot ->
            usuari = documentSnapshot.toObject(Usuari::class.java)
            controlador.setUsuariActiu(usuari)
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

    fun recuperarContra(correu: String,context: Context){
        FirebaseAuth.getInstance().sendPasswordResetEmail(correu)
            .addOnCompleteListener { task ->
                if(task.isSuccessful){
                    Toast.makeText(context,"CORREU ENVIAT", Toast.LENGTH_LONG).show()}
                else{
                    Toast.makeText(context,"CORREU ERRONI o s'acaba d'enviar", Toast.LENGTH_LONG).show()}
            }

    }
}