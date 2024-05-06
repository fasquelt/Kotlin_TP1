package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.provider.AlarmClock.EXTRA_MESSAGE
import android.widget.EditText
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
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
    fun sendValue(){
        val text = binding.input.text.toString()
        val intent = Intent(this, AjoutContact::class.java).apply {
            putExtra("valeur", text);
        }
        startActivity(intent)
    }
}