package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import com.example.practica04.R
import com.example.practica04.databinding.CustomOrderDialogBinding
import com.example.practica04.model.CompatiblePlatform

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
                    CompatiblePlatform.PLAYSTATION -> GamesFragment.gamesFragmentViewModel.filterGames(
                        CompatiblePlatform.PLAYSTATION
                    )

                    CompatiblePlatform.XBOX -> GamesFragment.gamesFragmentViewModel.filterGames(
                        CompatiblePlatform.XBOX
                    )

                    CompatiblePlatform.NINTENDO -> GamesFragment.gamesFragmentViewModel.filterGames(
                        CompatiblePlatform.NINTENDO
                    )

                    CompatiblePlatform.ALL -> GamesFragment.gamesFragmentViewModel.filterGames(
                        CompatiblePlatform.ALL
                    )

                    else -> GamesFragment.gamesFragmentViewModel.restoreGameList()
                }
                dismiss()
            }
        }
        return dialog
    }
}