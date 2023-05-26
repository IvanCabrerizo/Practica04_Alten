package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.practica04.R
import com.example.practica04.databinding.CustomOrderDialogBinding

class GamesFilterFragmentDialog : DialogFragment() {

    private val customOrderDialogBinding by lazy { CustomOrderDialogBinding.inflate(layoutInflater) }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = Dialog(requireContext(), R.style.FullScreenDialog)
        dialog.setContentView(customOrderDialogBinding.root)
        dialog.setCancelable(true)

        with(customOrderDialogBinding) {
            orderDialogBtnPlayStation.setOnClickListener {
                GamesFragment.gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnPlayStation
                )
            }

            orderDialogBtnXbox.setOnClickListener {
                GamesFragment.gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnXbox
                )
            }

            orderDialogBtnNintendo.setOnClickListener {
                GamesFragment.gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnNintendo
                )
            }

            orderDialogBtnAllPlatform.setOnClickListener {
                GamesFragment.gamesFragmentViewModel.selectedFilter(
                    requireContext(),
                    orderDialogBtnAllPlatform
                )
            }

            orderDialogBtnClose.setOnClickListener {
                dismiss()
            }

            orderDialogBtnAccept.setOnClickListener {
                when (GamesFragment.gamesFragmentViewModel.selectFilter.value) {
                    "PlayStation" -> GamesFragment.gamesFragmentViewModel.filterGamesByPlayStation()
                    "Xbox" -> GamesFragment.gamesFragmentViewModel.filterGamesByXbox()
                    "Nintendo" -> GamesFragment.gamesFragmentViewModel.filterGamesByNintendo()
                    "Todas" -> GamesFragment.gamesFragmentViewModel.restoreGameList()
                }
                dismiss()
            }
        }
        return dialog
    }
}