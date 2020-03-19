package com.example.govegan

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Adapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.pagina_principal.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.pagina_principal)
    }

    fun accio1(view: View) {
        intent = Intent(this, Calendari_Setmanal::class.java)
        startActivity(intent)
    }

    fun accio2(view: View) {
        intent = Intent(this, Forum::class.java)
        startActivity(intent)
    }

    fun accio3(view: View) {
        intent = Intent(this, LlistaCompra::class.java)
        //aqui dona error FATAL EXCEPTION
        startActivity(intent)
    }

    fun accio4(view: View) {
        intent = Intent(this, Propostes::class.java)
        startActivity(intent)
    }

    fun accio5(view: View) {
        intent = Intent(this, layoutCuriositats::class.java)
        startActivity(intent)
    }


}
