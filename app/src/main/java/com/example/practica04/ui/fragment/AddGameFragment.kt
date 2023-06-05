package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.practica04.R
import com.example.practica04.databinding.FragmentAddGameBinding
import com.example.practica04.model.Pegi
import com.example.practica04.ui.viewmodel.AddGameFragmentViewModel

class AddGameFragment : Fragment() {

    private val addGameFragmentBinding by lazy { FragmentAddGameBinding.inflate(layoutInflater) }
    private val addGameFragmentViewModel: AddGameFragmentViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return addGameFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        addGameFragmentViewModel.getNewGame().observe(viewLifecycleOwner) { game ->
            if (game != null) {
                addGameFragmentViewModel.addGameRepository()
                addGameFragmentViewModel.resetNewGame()
                findNavController().navigateUp()
            }
        }

        addGameFragmentViewModel.getPegi().observe(viewLifecycleOwner) { pegi ->
            val imageView = addGameFragmentBinding.addGameFragmentBtnPegi
            when (pegi) {
                Pegi.PEGI3 -> imageView.setImageResource(R.drawable.pegi3)
                Pegi.PEGI7 -> imageView.setImageResource(R.drawable.pegi7)
                Pegi.PEGI12 -> imageView.setImageResource(R.drawable.pegi12)
                Pegi.PEGI16 -> imageView.setImageResource(R.drawable.pegi16)
                Pegi.PEGI18 -> imageView.setImageResource(R.drawable.pegi18)
            }
        }

        addGameFragmentViewModel.getNintendoSelected().observe(viewLifecycleOwner) {
            selectButtonBackground(addGameFragmentBinding.addGameFragmentBtnNintendo)
        }

        addGameFragmentViewModel.getPlayStationSelected().observe(viewLifecycleOwner) {
            selectButtonBackground(addGameFragmentBinding.addGameFragmentBtnPlayStation)
        }

        addGameFragmentViewModel.getXboxSelected().observe(viewLifecycleOwner) {
            selectButtonBackground(addGameFragmentBinding.addGameFragmentBtnXbox)
        }

        with(addGameFragmentBinding) {
            addGameFragmentBtnPegi.setOnClickListener {
                findNavController().navigate(R.id.action_addGameFragment_to_gamesPegiAddGameFragmentDialog)
            }

            addGameFragmentBtnNintendo.setOnClickListener {
                addGameFragmentViewModel.setNintendoCompatible()
            }

            addGameFragmentBtnPlayStation.setOnClickListener {
                addGameFragmentViewModel.setPlayStationCompatible()
            }

            addGameFragmentBtnXbox.setOnClickListener {
                addGameFragmentViewModel.setXboxCompatible()
            }

            addGameFragmentBtnSave.setOnClickListener {
                validateGameData()
                addGameFragmentViewModel.generateGame(
                    addGameFragmentBinding.addGameFragmentInputName.editText?.text.toString(),
                    addGameFragmentBinding.addGameFragmentInputStudio.editText?.text.toString(),
                    addGameFragmentBinding.addGameFragmentInputDate.editText?.text.toString(),
                    addGameFragmentBinding.addGameFragmentInputCover.editText?.text.toString()
                )
            }

            addGameFragmentBtnBack.setOnClickListener {
                findNavController().navigateUp()
            }
        }
    }

    private fun validateGameData() {
        val name = addGameFragmentBinding.addGameFragmentInputName.editText?.text.toString()
        val studio = addGameFragmentBinding.addGameFragmentInputStudio.editText?.text.toString()
        val date = addGameFragmentBinding.addGameFragmentInputDate.editText?.text.toString()
        val cover = addGameFragmentBinding.addGameFragmentInputCover.editText?.text.toString()

        if (name.isEmpty()) {
            addGameFragmentBinding.addGameFragmentInputName.error =
                getString(R.string.addGameFragmentNameError)
        } else {
            addGameFragmentBinding.addGameFragmentInputName.error = null
        }

        if (studio.isEmpty()) {
            addGameFragmentBinding.addGameFragmentInputStudio.error =
                getString(R.string.addGameFragmentStudioError)
        } else {
            addGameFragmentBinding.addGameFragmentInputStudio.error = null
        }

        if (date.isEmpty()) {
            addGameFragmentBinding.addGameFragmentInputDate.error =
                getString(R.string.addGameFragmentDateErrorEmpty)
        } else if (!addGameFragmentViewModel.isValidDate(date)) {
            addGameFragmentBinding.addGameFragmentInputDate.error =
                getString(R.string.addGameFragmentDateErrorFormat)
        } else {
            addGameFragmentBinding.addGameFragmentInputDate.error = null
        }

        if (cover.isEmpty()) {
            addGameFragmentBinding.addGameFragmentInputCover.error =
                getString(R.string.addGameFragmentCoverError)
        } else {
            addGameFragmentBinding.addGameFragmentInputCover.error = null
        }
    }

    private fun selectButtonBackground(button: ImageView) {
        if (button.background == null) {
            button.background =
                ContextCompat.getDrawable(button.context, R.drawable.selected_button_backgroun)
        } else {
            button.background = null
        }
    }
}