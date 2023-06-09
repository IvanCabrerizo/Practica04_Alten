package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.practica04.databinding.FragmentUserPreferenceBinding

class UserPreferenceFragment : Fragment() {

    private val userPreferenceBinding by lazy { FragmentUserPreferenceBinding.inflate(layoutInflater) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return userPreferenceBinding.root
    }
}