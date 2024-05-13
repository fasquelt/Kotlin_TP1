package com.example.tp1

import android.app.Activity
import android.view.View
import android.view.ViewGroup
import android.widget.*
class MyListAdapter(private val context: Activity, private val contacts: Array<Contact>)
    : ArrayAdapter<Contact>(context, R.layout.custom_list, contacts) {

    override fun getView(position: Int, view: View?, parent: ViewGroup): View {
        val inflater = context.layoutInflater
        val rowView = inflater.inflate(R.layout.custom_list, null, true)

        val titleText: TextView = rowView.findViewById(R.id.title)
        return rowView
    }
}