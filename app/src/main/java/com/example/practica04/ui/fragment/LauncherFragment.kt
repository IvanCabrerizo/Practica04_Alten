package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.practica04.databinding.FragmentLauncherBinding
import com.example.practica04.ui.viewmodel.LauncherFragmentViewModel

class LauncherFragment : Fragment() {

    private val binding by lazy { FragmentLauncherBinding.inflate(layoutInflater) }
    private val launcherViewModel: LauncherFragmentViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        launcherViewModel.navigateWithDelay(findNavController())
    }
}