package com.example.tp1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contacts : Array<Contact> = arrayOf(
        Contact("Test","Voila","02")
    )
    private val AjoutLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
            result -> if (result.resultCode == Activity.RESULT_OK){
            result.data?.also{ data ->
                val dataN = intent.getStringExtra("Nom")
                val dataP = intent.getStringExtra("Prenom")
                val dataT = intent.getStringExtra("Tel")
                val newContact = Contact(dataN.toString(), dataP.toString(), dataT.toString())
                contacts.fill(newContact,contacts.size, contacts.size+1)
            }
        }
    }
    private val intent = Intent(this, AjoutContact::class.java)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myListAdapter = MyListAdapter(this, contacts = contacts)
        binding.list.adapter = myListAdapter
        binding.boutonAcces.setOnClickListener {
            sendValue()
        }
        AjoutLauncher.launch(intent)
    }

    private fun sendValue(){
        val text = binding.input.text.toString()
        val intent = Intent(this, AjoutContact::class.java).apply {
            putExtra("prenom", text)
        }
        startActivity(intent)
    }
}