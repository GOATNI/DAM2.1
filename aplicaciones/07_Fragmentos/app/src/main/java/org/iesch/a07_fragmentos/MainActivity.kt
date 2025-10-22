package org.iesch.a07_fragmentos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.os.bundleOf
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.add
import androidx.fragment.app.commit
import org.iesch.a07_fragmentos.fragments.ADDRESS_BUNDLE
import org.iesch.a07_fragmentos.fragments.NAME_BUNDLE
import org.iesch.a07_fragmentos.fragments.PrimerFragment

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
        // 3
        val datos = bundleOf(
            NAME_BUNDLE to "Alberto",
            ADDRESS_BUNDLE to "mi casa"
        )

        // 2
        supportFragmentManager.commit {
            setReorderingAllowed(true)
            add<PrimerFragment>(R.id.fragmentContainer, args = datos)
        }
    }
}