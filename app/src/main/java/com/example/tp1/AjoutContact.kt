package com.example.tp1
import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.ContentValues
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
            // construction de la fenêtre de dialogue
            val builder = AlertDialog.Builder(it)
            // ajout des messages par défaut
            builder.setMessage("Confirmer l'ajout ? Une fois cette fenêtre fermée, cliquez sur le bouton valider pour revenir à l'accueil")
                .setPositiveButton("Oui") { dialog, which ->
                    AjoutContact.finished = true // puis du listener pour la validation
                }
                .setNegativeButton("Non") { dialog, id ->
                    // et de celui d'annulation
                }
            // création de la fenêtre afin de l'afficher par la suite
            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}


class AjoutContact : AppCompatActivity(){

    // ajout des variables : lien avec la vue
    private lateinit var binding: ActivityAjoutBinding
    // boutons et composants de l'interface qui seront utilisés
    lateinit var imageContact : ImageView
    lateinit var btnGal : Button
    lateinit var btnCam : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // utilisation du binding pour avoir la vue
        binding = ActivityAjoutBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // récupération des affichages en passant par les id
        imageContact = findViewById(R.id.image)
        btnGal = findViewById(R.id.gal)
        btnCam = findViewById(R.id.cam)

        // création du lancher pour le passage d'image
        val galleryActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) {
            if (it.resultCode == RESULT_OK) {
                val image_uri: Uri? = it.data?.data
                imageContact.setImageURI(image_uri)
            }

        }
        // ajout du listener pour le lancement de la caméra
        btnCam.setOnClickListener {
            if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
                PackageManager.PERMISSION_DENIED ||
                checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_DENIED
            ) {
                val permission = arrayOf<String>(
                    android.Manifest.permission.CAMERA,
                    android.Manifest.permission.WRITE_EXTERNAL_STORAGE
                )
                requestPermissions(permission, 112)
            } else {
                openCamera()
            }
        }


        // ajout du listener pour le lancement de la galerie

        btnGal.setOnClickListener {
            val galleryIntent =
                Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            galleryActivityResultLauncher.launch(galleryIntent)
        }

        // listener pour la validation de l'ajout du contact
        binding.btnval.setOnClickListener {
            if(binding.input1.text.isNullOrEmpty() || binding.input2.text.isNullOrEmpty()){
                // cas où l'un des champs nécessaires est manquant
                val snack = Snackbar.make(it,"Nom ou prénom manquant",Snackbar.LENGTH_LONG)
                snack.show()
                // affichage d'une snackbar d'erreur
            }
            else{
                StartGameDialogFragment().show(supportFragmentManager, "CONFIRMATION")
                // affichage de la fenetre de dialogue créée dans la classes précédente
                val nom = binding.input1.text.toString()
                val prenom = binding.input2.text.toString()
                val fullName = prenom+nom
                // récupération des valeurs afin d'en faire une chaîne qui sera transmise
                if (finished == true){
                    // vérification que l'utilisateur a confirmé et appel des fonctions graphiques
                    if (binding.bajout.isChecked){
                        confAddFav(prenom,nom)
                    }
                    else{
                        confCreation(prenom, nom)
                    }
                    // création de l'intent et ajout du paramètre
                    intent = Intent().apply {
                        putExtra("contact",fullName)
                    }
                    // mise à jour du résultat pour l'intent créé dans l'autre classe
                    setResult(Activity.RESULT_OK, intent)
                    // lancement de l'autre activité, qui permet de revenir à l'accueil
                    startActivity(intent)
                    finish()
                }
            }
        }
        // ajout des listeners pour changer l'image de profil en fonction du genre
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

    var image_uri: Uri? = null

    // lancement de la caméra
    private var cameraActivityResultLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) {
        if (it.resultCode == RESULT_OK) {
            imageContact.setImageURI(image_uri)
        }
    }
    // fonction pour appeler le launcher défini précédemment
    private fun openCamera() {
        val values = ContentValues()
        values.put(MediaStore.Images.Media.TITLE, "New Picture")
        values.put(MediaStore.Images.Media.DESCRIPTION, "From the Camera")
        image_uri = contentResolver.insert(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, values)
        val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
        cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, image_uri)
        cameraActivityResultLauncher.launch(cameraIntent)
    }

    // fonction graphiques pour afficher un toast en fonction de l'ajout ou non aux favoris

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

    // création et affichage de la popup pour saisir la date de naissance
    private fun setPopup() {
        val cal = Calendar.getInstance()
        val year = cal.get(Calendar.YEAR)
        val month = cal.get(Calendar.MONTH)
        val day = cal.get(Calendar.DAY_OF_MONTH)
        // récupération de la date entrée
        val datePicker = DatePickerDialog(this, { view, year, monthOfYear, dayOfMonth ->

            cal.set(Calendar.YEAR, year)
            cal.set(Calendar.MONTH, monthOfYear)
            cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

            val myFormat = "dd.MM.yyyy" // précision du format
            val sdf = SimpleDateFormat(myFormat, Locale.FRANCE)
            val date = sdf.format(cal.time)
            updateLabel(date) // mise à jour du champ texte par la fonction
        }, year, month, day)
        datePicker.show()
    }

    private fun updateLabel(v : String) {
        binding.input3.setText(v)
    }

    // déclaration de la variable globale qui permet de savoir si l'utilisateur a validé ou non
    companion object {
        var finished = false
    }

}