package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica04.R
import com.example.practica04.databinding.FragmentGamesByStudioBinding
import com.example.practica04.ui.adapter.GameByStudioListAdapter
import com.example.practica04.ui.viewmodel.GamesByStudioFragmentViewModel

class GamesByStudioFragment : Fragment() {

    private val gamesByStudioFragmentBinding by lazy {
        FragmentGamesByStudioBinding.inflate(
            layoutInflater
        )
    }
    private val gamesByStudioFragmentViewModel: GamesByStudioFragmentViewModel by viewModels()
    private val gamesByStudioAdapter = GameByStudioListAdapter()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return gamesByStudioFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val studioName = arguments?.getString(getString(R.string.argument_studioname))

        with(gamesByStudioFragmentBinding.gamesFragmentListGames) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gamesByStudioAdapter
        }

        gamesByStudioFragmentViewModel.getGameList().observe(viewLifecycleOwner) { gameList ->
            gamesByStudioAdapter.submitList(gameList)
        }
        gamesByStudioFragmentViewModel.getGames(studioName ?: getString(R.string.not_found))

        gamesByStudioFragmentBinding.gamesFragmentLabelTitle.text = studioName
        gamesByStudioFragmentBinding.gamesByStudioFragmentBtnBack.setOnClickListener {
            findNavController().navigateUp()
        }
    }
}