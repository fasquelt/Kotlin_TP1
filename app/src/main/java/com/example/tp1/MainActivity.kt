package com.example.tp1


import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tp1.databinding.ActivityMainBinding


// classe d'accueil du projet, qui permttra d'accéder au formulaire d'ajout d'un contac
class MainActivity : AppCompatActivity() {

    // initialisation des variables
    private lateinit var binding: ActivityMainBinding // lien avec la vue

    // fonction de création
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // utilisation du fichier XML en passant par le binding pour la vue
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        // ajout du listener pour l'accès au formulaire et la transmission du prénom du contact
        binding.boutonAcces.setOnClickListener {
            sendValue()
        }
        // vérification des permissions pour la caméra et l'accès à la galerie

        if (checkSelfPermission(android.Manifest.permission.CAMERA) ==
            PackageManager.PERMISSION_DENIED ||
            checkSelfPermission(android.Manifest.permission.WRITE_EXTERNAL_STORAGE)
            == PackageManager.PERMISSION_DENIED) {
            val permission = arrayOf<String>(
                android.Manifest.permission.CAMERA,
                android.Manifest.permission.WRITE_EXTERNAL_STORAGE
            )
            requestPermissions(permission, 112)
        }

    }

    // fonction d'envoi de la valeur entrée dans le champ texte de la page d'accueil
    // et qui sera affecté au champ "nom"
    private fun sendValue(){
        val text = binding.input.text.toString()
        val intentEnvoiNom = Intent(this, AjoutContact::class.java).apply {
            putExtra("prenom", text)
        }
        startActivity(intentEnvoiNom) // lancement de l'intent lié à la classe AjoutContact
        // qui est défini au-dessus
    }
}