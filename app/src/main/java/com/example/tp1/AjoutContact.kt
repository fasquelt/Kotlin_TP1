package com.example.tp1
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.example.tp1.databinding.ActivityAjoutBinding
import com.google.android.material.snackbar.Snackbar
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

class StartGameDialogFragment : DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Use the Builder class for convenient dialog construction.
            val builder = AlertDialog.Builder(it)
            builder.setMessage("Confirmer l'ajout ? Une fois cette fenêtre fermée, cliquez sur le bouton valider pour revenir à l'accueil")
                .setPositiveButton("Oui") { dialog, which ->
                    AjoutContact.finished = true
                }
                .setNegativeButton("Non") { dialog, id ->
                    // User cancelled the dialog.
                }
            // Create the AlertDialog object and return it.
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


class AjoutContact : AppCompatActivity(){

    private lateinit var binding: ActivityAjoutBinding

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
                StartGameDialogFragment().show(supportFragmentManager, "CONFIRMATION")
                val nom = binding.input1.text.toString()
                val prenom = binding.input2.text.toString()
                val fullName = prenom+nom
                if (finished == true){
                    if (binding.bajout.isChecked){
                        confAddFav(prenom,nom)
                    }
                    else{
                        confCreation(prenom, nom)
                    }
                    intent = Intent().apply {
                        putExtra("contact",fullName)
                    }
                    setResult(Activity.RESULT_OK, intent)
                    startActivity(intent)
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

    private fun confCreation(p : String, n : String){
        val text = p+" "+n+" sauvegardé !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun confAddFav(p : String, n : String){
        val text = p+" "+n+" sauvegardé dans les favoris !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun setPopup() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        val datePicker = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->

            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // mention the format you need
            val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
            val date = sdf.format(cal.time)
            updateLabel(date)
        }, year, month, day)
        datePicker.show()
    }

    private fun updateLabel(v : String) {
        binding.input3.setText(v)
    }

    companion object {
        var finished = false
    }

}