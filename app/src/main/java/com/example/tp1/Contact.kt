package com.example.tp1

import java.io.Serializable

data class Contact
    (val name : String, val firstname : String, val tel : String)
    : Serializable {
        companion object{
            var contacts : MutableList<Contact> = mutableListOf(
                Contact("Exemple1", "Voila", "03"),
                Contact("Exemple2","Voila","02"),
            )
        }
    }


