package com.example.tp1

import ContactAdapter
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.tp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contacts : MutableList<Contact> = mutableListOf(
        Contact("Test","Voila","02"),
        Contact("Ici", "Voila", "03")
    )


    private val intent = Intent(this, AjoutContact::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myAdapter = ContactAdapter()
        binding.recyclerView.adapter = myAdapter
        binding.recyclerView.layoutManager = LinearLayoutManager(this)
        myAdapter.submitList(contacts)
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
}