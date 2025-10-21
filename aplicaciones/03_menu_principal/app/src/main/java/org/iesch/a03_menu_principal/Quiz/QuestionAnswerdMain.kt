package org.iesch.a03_menu_principal.Quiz

import android.app.Activity
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.iesch.a03_menu_principal.databinding.QuestionAnswerdBinding

class QuestionAnswerdMain : AppCompatActivity() {

    private lateinit var binding: QuestionAnswerdBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = QuestionAnswerdBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val answerText = intent.getStringExtra("answer_text") ?: "No answer"
        binding.tvAnswer.text = answerText

        binding.btnextquestion.setOnClickListener {
            setResult(Activity.RESULT_OK) // <- devolvemos resultado
            finish() // <- volvemos al quiz
        }
    }
}
