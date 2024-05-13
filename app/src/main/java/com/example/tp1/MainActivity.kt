package com.example.tp1

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.widget.ListView
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding
import com.example.tp1.Contact

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    var contacts : Array<Contact> = arrayOf(
        Contact("Test","Voila","02")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val myListAdapter = MyListAdapter(this, contacts = contacts)
        binding.list.adapter = myListAdapter
        binding.boutonAcces.setOnClickListener {
            sendValue()
        }
    }
    private val AjoutLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){
        result -> if (result.resultCode == Activity.RESULT_OK){
            result.data?.also{
                data -> val testData = data.getStringExtra(("prenom"))
            }
        }
    }
    val intent = Intent(this, AjoutContact::class.java)
    AjoutLauncher.launch(intent)
}