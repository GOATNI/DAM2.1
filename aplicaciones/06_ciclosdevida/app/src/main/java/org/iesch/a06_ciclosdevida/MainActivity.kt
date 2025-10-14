package org.iesch.a06_ciclosdevida

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        Log.i("Ciclovida","Entramos en el metodo on create")

    }

    override fun onStart() {
        super.onStart()

        Log.w("ciclovida","Entramos en el metodo onstart")
    }

    override fun onResume() {
        super.onResume()

        Log.e("Ciclovida","Entramos en el metodo onresum")
    }

    override fun onPause() {
        super.onPause()
        Log.e("Ciclovida","Entramos en el metodo onpause")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e("Ciclovida","Entramos en el metodo onrestart")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e("Ciclovida","Entramos en el metodo onDestroy")
    }
}