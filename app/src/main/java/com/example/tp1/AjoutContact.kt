package com.example.tp1

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityAjoutBinding

class AjoutContact : AppCompatActivity() {

    private lateinit var binding: ActivityAjoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val intent = Intent(this, AjoutContact::class.java)
        binding.btnval.setOnClickListener {
            confCreation()
            if (binding.bajout.isChecked){
                confAddFav()
            }
        }
        val prenom = intent.getStringExtra("valeur")
        binding.input1.setText(prenom)
    }

    fun confCreation(){
        val text = "Contact sauvegardé !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    fun confAddFav(){
        val text = "Contact ajouté aux favoris !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }








}