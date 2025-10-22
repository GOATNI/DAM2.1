package org.iesch.a03_menu_principal.Quiz

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import org.iesch.a03_menu_principal.databinding.ActivityFinishBinding

class Finish : AppCompatActivity() {
    private lateinit var binding: ActivityFinishBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFinishBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.startAgain.setOnClickListener {
            val intent = Intent(this, quizmain::class.java)
            startActivity(intent)
            finish()
        }
    }
}
