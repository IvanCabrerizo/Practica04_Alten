package com.example.practica04.ui.fragment

import android.app.Dialog
import android.os.Bundle
import android.widget.Button
import androidx.core.content.ContextCompat
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
                    orderDialogBtnPlayStation
                )
                updateFilterBtnBackground(
                    orderDialogBtnPlayStation,
                    gamesFragmentViewModel.previousSelectedFilter.value
                )
            }

            orderDialogBtnXbox.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    orderDialogBtnXbox
                )
                updateFilterBtnBackground(
                    orderDialogBtnXbox,
                    gamesFragmentViewModel.previousSelectedFilter.value
                )
            }

            orderDialogBtnNintendo.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    orderDialogBtnNintendo
                )
                updateFilterBtnBackground(
                    orderDialogBtnNintendo,
                    gamesFragmentViewModel.previousSelectedFilter.value
                )
            }

            orderDialogBtnAllPlatform.setOnClickListener {
                gamesFragmentViewModel.selectedFilter(
                    orderDialogBtnAllPlatform
                )
                updateFilterBtnBackground(
                    orderDialogBtnAllPlatform,
                    gamesFragmentViewModel.previousSelectedFilter.value
                )
            }

            orderDialogBtnClose.setOnClickListener {
                dismiss()
            }

            orderDialogBtnAccept.setOnClickListener {
                gamesFragmentViewModel.filterGames(
                    gamesFragmentViewModel.selectFilter.value ?: return@setOnClickListener
                )
                dismiss()
            }
        }
        return dialog
    }

    private fun updateFilterBtnBackground(newBtn: Button, prevBtn: Button?) {
        if (newBtn != prevBtn) {
            newBtn.background =
                ContextCompat.getDrawable(newBtn.context, R.drawable.selected_button_backgroun)
            prevBtn?.background = null
        }
    }
}