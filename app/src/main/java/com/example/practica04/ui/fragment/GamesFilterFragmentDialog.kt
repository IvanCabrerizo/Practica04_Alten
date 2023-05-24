package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.practica04.databinding.CustomOrderDialogBinding

class GamesFilterFragmentDialog : DialogFragment() {

    val binding by lazy { CustomOrderDialogBinding.inflate(layoutInflater) }
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext())
        dialog.setContentView(binding.root)
        dialog.setCancelable(true)

        return dialog
    }
}