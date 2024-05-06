package com.example.tp1

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.tp1.databinding.ActivityAjoutBinding

class AjoutContact : AppCompatActivity() {

    private lateinit var binding: ActivityAjoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}