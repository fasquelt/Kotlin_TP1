package com.example.tp1

import android.app.AlertDialog
import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment

class NoticeDialogFragment : DialogFragment() {
    internal lateinit var listener: NoticeDialogListener

    interface NoticeDialogListener {
        fun onDialogPositiveClick(dialog: DialogFragment)
        fun onDialogNegativeClick(dialog: DialogFragment)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            // Build the dialog and set up the button click handlers.
            val builder = AlertDialog.Builder(it)

            builder.setMessage("Confirmer l'ajout ?")
                .setPositiveButton(" Oui ",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the positive button event back to the
                        // host activity.
                        listener.onDialogPositiveClick(this)
                    })
                .setNegativeButton(" Non ",
                    DialogInterface.OnClickListener { dialog, id ->
                        // Send the negative button event back to the
                        // host activity.
                        listener.onDialogNegativeClick(this)
                    })

            builder.create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // Verify that the host activity implements the callback interface.
        try {
            // Instantiate the NoticeDialogListener so you can send events to
            // the host.
            listener = context as NoticeDialogListener
        } catch (e: ClassCastException) {
            // The activity doesn't implement the interface. Throw exception.
            throw ClassCastException((context.toString() +
                    " must implement NoticeDialogListener"))
        }
    }
}
