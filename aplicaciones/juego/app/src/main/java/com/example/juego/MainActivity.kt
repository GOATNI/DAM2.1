package com.example.juego

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.isEmpty
import com.example.juego.Modelo.Questions
import com.example.juego.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        var currentIndex = 0
        val quiz = Questions()

        fun showQuestion(index: Int) {
            binding.numPregunta.text = " ${index + 1} / ${quiz.questions.size}"
            binding.tvequation.text = quiz.questions[index]
            binding.rbfirst.text = quiz.choices[index][0]
            binding.rbsecond.text = quiz.choices[index][1]
            binding.tvDescription.text = quiz.descriptions[index]
            binding.rgoptions.clearCheck()
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

                val resultText = if (selected == correctAnswer) "Right" else "Wrong"

                val intent = Intent(this, QuestionAnswerdMain::class.java)
                intent.putExtra("answer_text", resultText)
                startActivity(intent)

                if (currentIndex < quiz.questions.size - 1) {
                    currentIndex++
                    showQuestion(currentIndex)
                } else {
                    if (currentIndex >= quiz.questions.size -

                        1) {
                        val intent = Intent(this, Finish::class.java)
                        startActivity(intent)
                    } else {
                        currentIndex++
                        showQuestion(currentIndex)
                    }

                }
            }
        }

    }




}
