package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.practica04.databinding.FragmentAddGameBinding
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


    }
}