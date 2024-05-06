package com.example.tp1
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityAjoutBinding
import com.google.android.material.snackbar.Snackbar

class AjoutContact : AppCompatActivity() {

    private lateinit var binding: ActivityAjoutBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnval.setOnClickListener {
            if(binding.input1.text.isEmpty() || binding.input2.text.isEmpty()){
                val snack = Snackbar.make(it,"Nom ou prénom manquant",Snackbar.LENGTH_LONG)
                snack.show()
            }
            else{
                if (binding.bajout.isChecked){
                    confCreationCourt()
                    confAddFav()
                }
                else{
                    confCreationLong()
                }
            }
        }
        val prenom = intent.getStringExtra("prenom")
        binding.input2.setText(prenom)
    }

    private fun confCreationCourt(){
        val text = "Contact sauvegardé !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun confCreationLong(){
        val text = "Contact sauvegardé !"
        val duration = Toast.LENGTH_LONG
        Toast.makeText(this, text, duration).show()
    }

    private fun confAddFav(){
        val text = "Contact ajouté aux favoris !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }








}