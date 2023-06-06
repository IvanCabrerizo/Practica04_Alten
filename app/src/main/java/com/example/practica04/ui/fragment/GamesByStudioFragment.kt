package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica04.R
import com.example.practica04.databinding.FragmentGamesByStudioBinding
import com.example.practica04.ui.adapter.GameByStudioListAdapter

class GamesByStudioFragment : Fragment() {

    private val gamesByStudioFragmentBinding by lazy {
        FragmentGamesByStudioBinding.inflate(
            layoutInflater
        )
    }
    private val gamesByStudioAdapter = GameByStudioListAdapter()
    private val studioName = arguments?.getString(getString(R.string.argument_studioname))

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return gamesByStudioFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        with(gamesByStudioFragmentBinding.gamesFragmentListGames) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gamesByStudioAdapter
        }

        gamesByStudioFragmentBinding.gamesFragmentLabelTitle.text = studioName

    }
}