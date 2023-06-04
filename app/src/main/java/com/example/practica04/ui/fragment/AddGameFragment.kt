package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.practica04.R
import com.example.practica04.databinding.FragmentAddGameBinding
import com.example.practica04.ui.viewmodel.AddGameFragmentViewModel

class AddGameFragment : Fragment() {

    private val addGameFragmentBinding by lazy { FragmentAddGameBinding.inflate(layoutInflater) }
    private val addGameFragmentViewModel: AddGameFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return addGameFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        /*        addGameFragmentViewModel.getName().observe(viewLifecycleOwner) {
                    addGameFragmentViewModel.setName(addGameFragmentBinding.addGameFragmentInputName.editText?.text.toString())
                }

                addGameFragmentViewModel.getStudio().observe(viewLifecycleOwner) {
                    addGameFragmentViewModel.setStudio(addGameFragmentBinding.addGameFragmentInputStudio.editText?.text.toString())
                }

                addGameFragmentViewModel.getDate().observe(viewLifecycleOwner) {
                    addGameFragmentViewModel.setDate(addGameFragmentBinding.addGameFragmentInputDate.editText?.text.toString())
                }

                addGameFragmentViewModel.getCover().observe(viewLifecycleOwner) {
                    addGameFragmentViewModel.setCover(addGameFragmentBinding.addGameFragmentInputCover.editText?.text.toString())
                }*/

        addGameFragmentBinding.addGameFragmentBtnSave.setOnClickListener {
            validateGameData()
            addGameFragmentViewModel.generateGame()
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

        addGameFragmentViewModel.setName(name)
        addGameFragmentViewModel.setStudio(studio)
        addGameFragmentViewModel.setDate(date)
        addGameFragmentViewModel.setCover(cover)
    }
}