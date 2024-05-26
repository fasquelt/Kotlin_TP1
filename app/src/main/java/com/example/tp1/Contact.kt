package com.example.tp1

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import java.io.Serializable

data class Contact
    (val name : String, val firstname : String, val tel : String)
    : Serializable

class ContactViewHolder(view: View) : ViewHolder(view) {
    val nomTextView: TextView = view.findViewById(R.id.nom)
    val prenomTextView: TextView = view.findViewById(R.id.prenom)
    val telTextView: TextView = view.findViewById(R.id.tel)
}