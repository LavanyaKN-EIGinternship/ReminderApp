package com.happiestminds.remainder

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment

class MyDialog: DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        var dlg: Dialog? = null

       val message = arguments?.getString("msg")
        val title = arguments?.getString("title")
        val descr = arguments?.getString("description")
        val date = arguments?.getString("date")
        val time = arguments?.getString("time")

        val rem = Remind(title!!,descr!!,date!!,time!!)

        //create a dialog here

        context?.let {
            val builder = AlertDialog.Builder(it)
            builder.setTitle("Confirmation")
            builder.setMessage(message)
            builder.setPositiveButton("yes"){ dialog, i ->
                // executed button clicking
                DBWrapper(it).deleteRemainder(rem)
                activity?.finish()


            }

            builder.setNegativeButton("No") { dialog, i ->
                dialog.cancel()
            }

            builder.setNeutralButton("Cancel") { dialg, i ->
                dialg.cancel()

            }
            dlg = builder.create()
        }
        return dlg!!
    }
}