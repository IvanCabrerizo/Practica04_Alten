package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.practica04.R
import com.example.practica04.databinding.CustomPegiDialogBinding
import com.example.practica04.model.Pegi
import com.example.practica04.ui.viewmodel.AddGameFragmentViewModel

class GamesPegiAddGameFragmentDialog : DialogFragment() {

    private val addGameFragmentViewModel: AddGameFragmentViewModel by activityViewModels()
    private val customPegiDialogBinding by lazy { CustomPegiDialogBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialog)
        dialog.setContentView(customPegiDialogBinding.root)
        dialog.setCancelable(true)

        with(customPegiDialogBinding) {
            gamesPegiAddGameFragmentDialogImgPegi3.setOnClickListener {
                addGameFragmentViewModel.setPegi(Pegi.PEGI3)
                dismiss()
            }

            gamesPegiAddGameFragmentDialogImgPegi7.setOnClickListener {
                addGameFragmentViewModel.setPegi(Pegi.PEGI7)
                dismiss()
            }

            gamesPegiAddGameFragmentDialogImgPegi12.setOnClickListener {
                addGameFragmentViewModel.setPegi(Pegi.PEGI12)
                dismiss()
            }

            gamesPegiAddGameFragmentDialogImgPegi16.setOnClickListener {
                addGameFragmentViewModel.setPegi(Pegi.PEGI16)
                dismiss()
            }

            gamesPegiAddGameFragmentDialogImgPegi18.setOnClickListener {
                addGameFragmentViewModel.setPegi(Pegi.PEGI18)
                dismiss()
            }
        }
        return dialog
    }
}