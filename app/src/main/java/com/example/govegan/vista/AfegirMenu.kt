package com.example.govegan.vista

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R

class AfegirMenu : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afegir_menu)
        val dialog =  AlertDialog.Builder(this)
        val dialogView = layoutInflater.inflate(R.layout.dialog_ingredients,null)
        dialog.setView(dialogView)
        dialog.setCancelable(false)
        dialog.show()

    }

    fun calendari(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }

    fun recepta(view: View){
        intent = Intent(this, Recepta::class.java)
        startActivity(intent)
    }
}