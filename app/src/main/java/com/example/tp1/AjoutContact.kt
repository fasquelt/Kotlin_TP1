package com.example.tp1
import android.app.DatePickerDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityAjoutBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale


class AjoutContact : AppCompatActivity() {

    private lateinit var binding: ActivityAjoutBinding
    val myCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAjoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnval.setOnClickListener {
            if(binding.input1.text.isNullOrEmpty() || binding.input2.text.isNullOrEmpty()){
                val snack = Snackbar.make(it,"Nom ou prénom manquant",Snackbar.LENGTH_LONG)
                snack.show()
            }
            else{
                if (binding.bajout.isChecked){
                    confAddFav()
                }
                else{
                    confCreation()
                }
            }
        }
        val prenom = intent.getStringExtra("prenom")
        binding.input2.setText(prenom)
        binding.input3.setOnClickListener {
            setPopup()
        }
        binding.f.setOnClickListener {
            binding.image.setImageResource(R.drawable.femme)
        }
        binding.h.setOnClickListener {
            binding.image.setImageResource(R.drawable.homme)
        }
        binding.a.setOnClickListener {
            binding.image.setImageResource(R.drawable.lgbt)
        }
    }

    private fun confCreation(){
        val text = "Contact sauvegardé !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun confAddFav(){
        val text = "Contact créé et ajouté aux favoris !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun setPopup() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->
            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)
            val myFormat = "dd.mm.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
            Toast.makeText(this, sdf.format(cal.time), Toast.LENGTH_LONG).show()

        }, year, month, day).show()
        updateLabel()
    }

    private fun updateLabel() {
        val myFormat = "MM/dd/yy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.FRANCE)
        binding.input3.setText(dateFormat.format(myCalendar.time))
    }
}