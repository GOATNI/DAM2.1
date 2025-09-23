package org.iesch.edadcanina

import android.content.Context
import android.inputmethodservice.Keyboard
import android.os.Bundle
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
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

        //tomammos el control de todas las aplicaciones
        val resultText = findViewById<TextView>(R.id.resultado)

        val calcularBotton = findViewById<Button>(R.id.calcularButton)

        val etEdad = findViewById<EditText>(R.id.etEdad)

        //los botones tienen la propiedad set o click listener para pulsarlos

        calcularBotton.setOnClickListener {
            //aqui va el codigo de lo que queremos hacer al pulsar el boton de calcular
            val edadString =  etEdad.text.toString();
            if(edadString.isEmpty()){
                //en caso de erro mando un mensaje toast
                Toast.makeText(this,R.string.toast_text, Toast.LENGTH_LONG).show()
            }else {
                val edadInt = edadString.toInt();
                val edadresultado = edadInt * 7
                val resultadoString = getString(R.string.resultado_format,edadresultado)
                resultText.setText(resultadoString)

                (getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager).hideSoftInputFromWindow(currentFocus?.windowToken,0)
            }
        }


    }


}