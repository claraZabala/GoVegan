package com.example.govegan.vista

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.govegan.R
import com.example.govegan.controlador.Controlador
import com.example.govegan.controlador.Controlador.toast
import kotlinx.android.synthetic.main.afegir_proposta.*
import kotlinx.android.synthetic.main.afegir_proposta.floatingAfegirIngredients
import kotlinx.android.synthetic.main.dialog_ingredients.view.*
import java.net.URI


class AfegirProposta : AppCompatActivity() {
    var llistaIngredients:ArrayList<String> = ArrayList()
    var controlador:Controlador = Controlador
    var llistaIngredientsCompra:ArrayList<String> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.afegir_proposta)
        llistaIngredients = controlador.getAllIngredientsByName()
        llistaIngredientsCompra = ArrayList()


        floatingAfegirIngredients.setOnClickListener {
            val dialog = AlertDialog.Builder(this)
            val dialogView = layoutInflater.inflate(R.layout.dialog_ingredients, null)
            dialog.setView(dialogView)
            dialog.setCancelable(false)
            val mAlertDialog = dialog.show()
            val customDialog = dialog.create()
            actualitzarLlistaIngredients(dialogView)


            dialogView.butAfegirIngredients.setOnClickListener{
                mAlertDialog.dismiss()
            }
            dialogView.floatingNousIngredients.setOnClickListener{
                AfegirNouIngredientLlista(dialogView)
            }
            dialogView.floatingLupa.setOnClickListener{
                buscarIngredient(dialogView)
            }
        }

    }

    fun buscarIngredient(dialogView: View) {
        if (dialogView.textCercaIngredient.text.toString().length == 0) {
            actualitzarLlistaIngredients(dialogView)
        }

        else {
            for (i in llistaIngredients) {
                if (i.equals(dialogView.textCercaIngredient.text.toString())) {
                    dialogView.layoutIngredientsBD.removeAllViews()
                    val btnIngredient: CheckBox = CheckBox(this)
                    btnIngredient.text = i
                    dialogView.layoutIngredientsBD.addView(btnIngredient)
                }
            }
        }
        dialogView.textCercaIngredient.setText("")
    }

    fun actualitzarLlistaIngredients(dialogView: View){
        dialogView.layoutIngredientsBD.removeAllViews()
        for (i in llistaIngredients) {
            val btnIngredient: CheckBox = CheckBox(this)
            btnIngredient.text = i
            dialogView.layoutIngredientsBD.addView(btnIngredient)
            if (i in llistaIngredientsCompra)
                btnIngredient.isChecked = true
                 btnIngredient.setOnClickListener {
                if (btnIngredient.isChecked) {
                    llistaIngredientsCompra.add(btnIngredient.text.toString())
                    textIngredients.text = llistaIngredientsCompra.toString()
                }
                if (!btnIngredient.isChecked) {
                    llistaIngredientsCompra.remove(btnIngredient.text.toString())
                    textIngredients.text = llistaIngredientsCompra.toString()
                }
            }
        }
    }

    fun AfegirNouIngredientLlista(dialogView: View){
        if (!dialogView.textAfegirNousIngredients.toString().equals("")){
            val chkBoxNouIngredient: CheckBox = CheckBox(this)
            chkBoxNouIngredient.text = dialogView.textAfegirNousIngredients.text.toString()
            if(dialogView.textAfegirNousIngredients.text.toString() !in llistaIngredients) {
                dialogView.layoutIngredientsBD.addView(chkBoxNouIngredient)
                llistaIngredients.add(chkBoxNouIngredient.text.toString())
                llistaIngredientsCompra.add(chkBoxNouIngredient.text.toString())
                controlador.addNouIngredientSenseFoto(chkBoxNouIngredient.text.toString())
                textIngredients.text = llistaIngredientsCompra.toString()
            }
        }
        dialogView.textAfegirNousIngredients.setText("")
    }

    fun recepta(view: View){
        val nom: String = resposta.text.toString()
        val pasos: String = pasos.text.toString()
        val tempsPrep: String = temps_prep.text.toString()
        val tempsCuina: String = temps_cuina.text.toString()
        val comensals: String = comensals.text.toString()
        var tipusRecepta = "4"
        if (teCarn.isChecked){
            tipusRecepta = "2"
        }
        if (teDerivats.isChecked){
            tipusRecepta = "1"
        }
        if (!teDerivats.isChecked and !teCarn.isChecked){
            tipusRecepta = "0"
        }
        val ingredients: ArrayList<String> = llistaIngredientsCompra
        val result = controlador.afegirReceptaNova(nom,pasos,tempsPrep,tempsCuina,comensals,tipusRecepta,ingredients)
        if (result==1){
            toast("Has d'omplir tots els camps")
        } else if(result==2){
            toast("El nom de recepta ja existeix")
        } else if (result==0) {
            intent = Intent(this, PaginaPrincipal::class.java)
            startActivity(intent)
        }
    }

    fun afegirImatge(view: View) {
        selectImageInAlbum()

    }

    fun selectImageInAlbum() {
        val intent = Intent(Intent.ACTION_GET_CONTENT)
        intent.type = "image/*"
        if (intent.resolveActivity(packageManager) != null) {
            startActivityForResult(intent, REQUEST_SELECT_IMAGE_IN_ALBUM)
        }
    }
    fun takePhoto() {
        val intent1 = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        if (intent1.resolveActivity(packageManager) != null) {
            startActivityForResult(intent1, REQUEST_TAKE_PHOTO)
        }
    }
    companion object {
        private val REQUEST_TAKE_PHOTO = 0
        private val REQUEST_SELECT_IMAGE_IN_ALBUM = 1
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        var imatge: ImageView = ImageView(this)
        var params = ViewGroup.LayoutParams(
            300,
            300
        )
        imatge.layoutParams = params
        if(resultCode == Activity.RESULT_OK){
            if (data != null) {
                imatge.setImageURI(data.data)
                layoutNovaProposta.addView(imatge)
            }

        }
    }

}