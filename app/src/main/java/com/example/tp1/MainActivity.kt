package com.example.tp1

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.boutonAcces.setOnClickListener {
            sendValue()
        }
    }
    private fun sendValue(){
        val text = binding.input.text.toString()
        val intent = Intent(this, AjoutContact::class.java).apply {
            putExtra("prenom", text)
        }
        startActivity(intent)
    }

    val nouveauContact = intent.getSerializableExtra("nouveau")
}