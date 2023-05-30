package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.activityViewModels
import com.example.practica04.R
import com.example.practica04.databinding.CustomOrderDialogBinding
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesFilterFragmentDialog : DialogFragment() {

    private val gamesFragmentViewModel: GamesFragmentViewModel by activityViewModels()
    private val customOrderDialogBinding by lazy { CustomOrderDialogBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialog)
        dialog.setContentView(customOrderDialogBinding.root)
        dialog.setCancelable(true)

        with(customOrderDialogBinding) {
            orderDialogBtnPlayStation.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnPlayStation
                )
            }

            orderDialogBtnXbox.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnXbox
                )
            }

            orderDialogBtnNintendo.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnNintendo
                )
            }

            orderDialogBtnAllPlatform.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnAllPlatform
                )
            }

            orderDialogBtnClose.setOnClickListener {
                dismiss()
            }

            orderDialogBtnAccept.setOnClickListener {
                gamesFragmentViewModel.filterGames(gamesFragmentViewModel.selectFilter.value ?: return@setOnClickListener)
                dismiss()
            }
        }
        return dialog
    }
}