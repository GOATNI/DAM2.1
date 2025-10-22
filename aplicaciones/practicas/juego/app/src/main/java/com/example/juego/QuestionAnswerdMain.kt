package com.example.juego

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.toColorInt
import com.example.juego.databinding.QuestionAnswerdBinding

class QuestionAnswerdMain : AppCompatActivity() {

    private lateinit var binding: QuestionAnswerdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionAnswerdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example usage
        val answerText = intent.getStringExtra("answer_text") ?: "No answer"
        binding.tvAnswer.text = answerText
        binding.btnextquestion.setOnClickListener {
            finish()
        }
    }


}
