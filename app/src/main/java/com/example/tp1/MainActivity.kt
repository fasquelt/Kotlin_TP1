package com.example.tp1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val intentCode = "contact"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.boutonAcces.setOnClickListener {
            sendValue()
        }
    }
    private val recuperationLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if (result.resultCode == Activity.RESULT_OK){
                result.data?.also {
                    data -> val aff = data.getStringExtra(intentCode)
                }
            }
        }

    private fun getValue(){
        val intentRecupContact = Intent(this, AjoutContact::class.java)
        recuperationLauncher.launch(intentRecupContact)
    }

    private fun sendValue(){
        val text = binding.input.text.toString()
        val intentEnvoiNom = Intent(this, AjoutContact::class.java).apply {
            putExtra("prenom", text)
        }
        startActivity(intentEnvoiNom)
    }
}