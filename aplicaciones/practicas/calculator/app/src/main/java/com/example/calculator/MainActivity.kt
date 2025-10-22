package com.example.calculator

import android.content.Context
import android.os.Bundle
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import kotlin.math.roundToInt

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
        val resultado = findViewById<TextView>(R.id.Resultado)
        val numero1 = findViewById<EditText>(R.id.numer1)
        val numero2 = findViewById<EditText>(R.id.number2)
        val sumar = findViewById<Button>(R.id.sumar);

        val restar = findViewById<Button>(R.id.restar);

        val dividir = findViewById<Button>(R.id.dividir);

        val multiplicar = findViewById<Button>(R.id.multiplicar);

        fun View.hideKeyboard() {
            val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(windowToken, 0)
        }

        sumar.setOnClickListener {
            val numero = numero1.text.toString().toDouble();
            val numer_2 = numero2.text.toString().toDouble();
            if (numero1.toString().isEmpty() || numero2.toString().isEmpty()){
                Toast.makeText(this,"Uno de los numeros  numero no esta introducido", Toast.LENGTH_LONG).show()
            }else{
                val sumar = numero+numer_2
                if (sumar>0.0) {
                    resultado.setText("El Resultado es $sumar")
                    resultado.setTextColor("green".toColorInt())
                }else{
                    resultado.setText("El Resultado es $sumar")
                    resultado.setTextColor("red".toColorInt())
                }

            }
            it.hideKeyboard()


        }
        restar.setOnClickListener {
            val numero = numero1.text.toString().toDouble();
            val numer_2 = numero2.text.toString().toDouble();
            if (numero1.toString().isEmpty() || numero2.toString().isEmpty()){
                Toast.makeText(this,"Uno de los numeros  numero no esta introducido", Toast.LENGTH_LONG).show()
            }else{
                val resto = numero-numer_2
                if (resto>0.0) {
                    resultado.setText("El Resultado es $resto")
                    resultado.setTextColor("green".toColorInt())
                }else{
                    resultado.setText("El Resultado es $resto")
                    resultado.setTextColor("red".toColorInt())
                }
            }
            it.hideKeyboard()

        }
        multiplicar.setOnClickListener {
            val numero = numero1.text.toString().toDouble();
            val numer_2 = numero2.text.toString().toDouble();
            if (numero1.toString().isEmpty() || numero2.toString().isEmpty()){
                Toast.makeText(this,"Uno de los numeros  numero no esta introducido", Toast.LENGTH_LONG).show()
            }else{
                val multiply = numero*numer_2
                if (multiply>0.0) {
                    resultado.setText("El Resultado es $multiply")
                    resultado.setTextColor("green".toColorInt())
                }else{
                    resultado.setText("El Resultado es $multiply")
                    resultado.setTextColor("red".toColorInt())
                }
            }
            it.hideKeyboard()

        }
        dividir.setOnClickListener {
            val numero = numero1.text.toString().toDouble();
            val numer_2 = numero2.text.toString().toDouble();
            if (numero1.toString().isEmpty() || numero2.toString().isEmpty()){
                Toast.makeText(this,"Uno de los numeros  numero no esta introducido", Toast.LENGTH_LONG).show()
            }else if (numero2.text.toString().toDouble()==0.0){
                Toast.makeText(this,"el segundo numero no puede ser negativo", Toast.LENGTH_LONG).show()
            }
            else{
                val dvide= numero/numer_2
                if (dvide>0.0) {
                    resultado.setText("El Resultado es $dvide")
                    resultado.setTextColor("green".toColorInt())
                }else{
                    resultado.setText("El Resultado es $dvide")
                    resultado.setTextColor("red".toColorInt())
                }

            }
            it.hideKeyboard()

        }


    }
}