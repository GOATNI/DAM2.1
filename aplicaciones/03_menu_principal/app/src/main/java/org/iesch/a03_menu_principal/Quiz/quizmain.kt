package org.iesch.a03_menu_principal.Quiz

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import org.iesch.a03_menu_principal.Quiz.Modelo.Questions
import org.iesch.a03_menu_principal.R
import org.iesch.a03_menu_principal.databinding.ActivityQuizmainBinding

class quizmain : AppCompatActivity() {
    private lateinit var binding: ActivityQuizmainBinding
    private var currentIndex = 0
    private val quiz = Questions()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityQuizmainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        showQuestion(currentIndex)

        binding.tvMainbuttonSend.setOnClickListener {
            if (binding.rgoptions.checkedRadioButtonId == -1) {
                Toast.makeText(this, "Tienes que elegir una opción", Toast.LENGTH_SHORT).show()
            } else {
                val selected = when (binding.rgoptions.checkedRadioButtonId) {
                    R.id.rbfirst -> binding.rbfirst.text.toString()
                    R.id.rbsecond -> binding.rbsecond.text.toString()
                    else -> ""
                }

                val correctAnswer = quiz.answers[currentIndex]
                val resultText = if (selected == correctAnswer) "✅ Correcto" else "❌ Incorrecto"

                // Abrimos pantalla de resultado
                val intent = Intent(this, QuestionAnswerdMain::class.java)
                intent.putExtra("answer_text", resultText)
                startActivityForResult(intent, 100) // <- volvemos aquí luego
            }
        }
    }

    private fun showQuestion(index: Int) {
        binding.numPregunta.text = "${index + 1} / ${quiz.questions.size}"
        binding.tvequation.text = quiz.questions[index]
        binding.rbfirst.text = quiz.choices[index][0]
        binding.rbsecond.text = quiz.choices[index][1]
        binding.tvDescription.text = quiz.descriptions[index]
        binding.rgoptions.clearCheck()
    }

    // Cuando volvemos de la pantalla de respuesta
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == 100 && resultCode == Activity.RESULT_OK) {
            currentIndex++
            if (currentIndex < quiz.questions.size) {
                showQuestion(currentIndex)
            } else {
                val intent = Intent(this, Finish::class.java)
                startActivity(intent)
                finish()
            }
        }
    }
}
