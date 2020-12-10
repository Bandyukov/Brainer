package com.example.brainer.dialog

import android.app.AlertDialog
import android.app.Dialog
import android.content.SharedPreferences
import android.os.Bundle
import android.preference.PreferenceManager
import androidx.fragment.app.DialogFragment

class MyDialogFragment : DialogFragment() {

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val builder: AlertDialog.Builder = AlertDialog.Builder(activity)
        builder.setTitle("Рекорды")

        val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(this.requireContext())

        
        return super.onCreateDialog(savedInstanceState)
    }

}