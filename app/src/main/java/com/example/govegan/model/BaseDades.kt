package com.example.govegan.model

import android.content.Context
import android.util.Log
import android.widget.ImageView
import android.widget.Toast
import com.bumptech.glide.Glide
import com.example.govegan.controlador.Controlador
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.QueryDocumentSnapshot
import com.google.firebase.storage.FirebaseStorage

class BaseDades(private val db: FirebaseFirestore) {
    var controlador:Controlador = Controlador
    private var userID:String = ""
    var mStorage = FirebaseStorage.getInstance().reference

    companion object {
        private const val TAG = "DocSnippets"
    }
    fun carregarImatgeIngredient(context: Context,imatge:ImageView,lastPath:String?){
        if(lastPath != null) {
            val storageRef =
                FirebaseStorage.getInstance().reference
            storageRef.child("ingredients").child(lastPath).downloadUrl
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
    fun carregarImatgeCuriositat(context: Context,imatge:ImageView,lastPath:String?){
        if(lastPath != null) {
            val storageRef =
                FirebaseStorage.getInstance().reference
            storageRef.child("curiositats").child(lastPath).downloadUrl
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
    fun carregarImatgeRecepta(context: Context,imatge:ImageView,lastPath:String?){
        if(lastPath != null) {
            val storageRef =
            FirebaseStorage.getInstance().reference
            storageRef.child("fotosRecepta").child(lastPath).downloadUrl
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
        val usuari: Usuari? = controlador.getUsuariByName(controlador.getUsuariActiu()!!)
        if (usuari != null) {
            db.collection("users").document(userID).set(usuari)
        }
    }
    fun getAllIngredients():ArrayList<Ingredient>{
        val ingredients:ArrayList<Ingredient> = ArrayList()
        db.collection("ingredients").get().addOnSuccessListener {
            resultat->
            for(ingredient: QueryDocumentSnapshot in resultat){
                ingredients.add(ingredient.toObject(Ingredient::class.java))
            }
        }
        return ingredients

    }

    fun getAllPropostes():ArrayList<Proposta>{
        val propostes:ArrayList<Proposta> = ArrayList()
        db.collection("propostes").get().addOnSuccessListener {
                resultat->
            for(proposta: QueryDocumentSnapshot in resultat){
                propostes.add(proposta.toObject(Proposta::class.java))
            }
        }
        return propostes
    }

    fun getAllCuriositats():ArrayList<Curiositat>{
        val curiositats:ArrayList<Curiositat> = ArrayList()
        db.collection("curiositats").get().addOnSuccessListener {
                resultat->
            for(curiositat: QueryDocumentSnapshot in resultat){
                curiositats.add(curiositat.toObject(Curiositat::class.java))
            }
        }
        return curiositats
    }

    fun getAllPreguntes():ArrayList<Pregunta>{
        val preguntes:ArrayList<Pregunta> = ArrayList()
        db.collection("preguntes").get().addOnSuccessListener {
                resultat->
            for(pregunta: QueryDocumentSnapshot in resultat){
                preguntes.add(pregunta.toObject(Pregunta::class.java))
            }
        }
        return preguntes
    }


    fun addIngredients(ingredient: Ingredient){
        db.collection("ingredients").document(ingredient.nom).set(ingredient)
    }

    fun addProposta(proposta: Proposta){
        db.collection("propostes").document(proposta.title).set(proposta)
    }

    fun addCuriositat(curiositat: Curiositat){
        db.collection("curiositats").document(curiositat.title).set(curiositat)
    }

    fun addPregunta(pregunta: Pregunta){
        db.collection("preguntes").document(pregunta.idPregunta).set(pregunta)
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
                exists = if (document != null) {
                    Log.d(TAG, "DocumentSnapshot data: ${document.data}")
                    true
                } else {
                    Log.d(TAG, "No such document")
                    false
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