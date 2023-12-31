package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.practica04.R
import com.example.practica04.databinding.CustomDeleteDialogBinding
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesDeleteFragmentDialog : DialogFragment() {

    private val gamesFragmentViewModel: GamesFragmentViewModel by activityViewModels()
    private val customDeleteDialogBinding by lazy { CustomDeleteDialogBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialog)

        dialog.setContentView(customDeleteDialogBinding.root)
        dialog.setCancelable(true)

        val gameName = gamesFragmentViewModel.getItemRecyclerSelected().value?.name

        with(customDeleteDialogBinding) {
            orderDialogBtnClose.setOnClickListener {
                dismiss()
            }

            deleteDialogLabelConfirmation.text =
                getString(R.string.delete_confirmation_message, gameName)

            orderDialogBtnAccept.setOnClickListener {
                gamesFragmentViewModel.deleteGame()
                dismiss()
            }
        }
        return dialog
    }
}