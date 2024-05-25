package com.example.tp1
import android.app.DatePickerDialog
import android.content.Context
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


class AjoutContact : AppCompatActivity(), NoticeDialogFragment.NoticeDialogListener {

    private lateinit var binding: ActivityAjoutBinding
    val myCalendar = Calendar.getInstance()
    private var conf = false

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
                val dialog = NoticeDialogFragment();
                dialog.onCreateDialog(savedInstanceState);
                val button = dialog.listener
                val c = Contact(binding.input1.text.toString(), binding.input2.text.toString(), "test")
                dialog.show(this.supportFragmentManager, "Confirmer l'ajout ?")
                fun launchNextScreen(context : Context, contact : Contact): Intent {
                    val intent = Intent(this, MainActivity::class.java)
                    intent.putExtra("nouveau", c)
                    return intent
                }
                if (binding.bajout.isChecked && button.onDialogPositiveClick(dialog).equals("Oui")){
                    confAddFav();
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
        val p = binding.input1.text
        val n = binding.input2.text
        val text = "Contact "+p+" "+n+ " sauvegardé !"
        val duration = Toast.LENGTH_SHORT
        Toast.makeText(this, text, duration).show()
    }

    private fun confAddFav(){
        val p = binding.input1.text
        val n = binding.input2.text
        val text = "Contact "+p+" "+n+ " créé et ajouté aux favoris !"
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
        val myFormat = "dd/MM/yyyy"
        val dateFormat = SimpleDateFormat(myFormat, Locale.FRANCE)
        binding.input3.setText(dateFormat.format(myCalendar.time))
    }

    override fun onDialogPositiveClick(dialog: DialogFragment) {
        conf = true;
    }

    override fun onDialogNegativeClick(dialog: DialogFragment) {
        conf = false
    }
}