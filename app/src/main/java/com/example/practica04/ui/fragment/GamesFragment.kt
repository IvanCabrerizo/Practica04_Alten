package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica04.databinding.FragmentGamesBinding
import com.example.practica04.ui.adapter.GamesListAdapter
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesFragment : Fragment() {


    private val gamesFragmentViewModel: GamesFragmentViewModel by activityViewModels()
    private val binding by lazy { FragmentGamesBinding.inflate(layoutInflater) }
    private val gamesAdapter = GamesListAdapter()
    private lateinit var gamesFragmentNavController: NavController

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        gamesFragmentNavController = findNavController()

        with(binding.gamesFragmentListGames) {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = gamesAdapter
        }

        gamesFragmentViewModel.gamesList.observe(viewLifecycleOwner) { gameList ->
            gamesAdapter.submitList(gameList)
        }

        gamesFragmentViewModel.getGames()

        binding.gamesFragmentBtnIdOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGames(GamesFragmentViewModel.SortType.ID, binding.gamesFragmentListGames)
                selectedOrderBtn(
                    binding.gamesFragmentBtnIdOrder,
                    binding.gamesFragmentBtnAlphabetOrder,
                    requireContext()
                )
            }
        }

        binding.gamesFragmentBtnAlphabetOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGames(GamesFragmentViewModel.SortType.NAME, binding.gamesFragmentListGames)
                selectedOrderBtn(
                    binding.gamesFragmentBtnIdOrder,
                    binding.gamesFragmentBtnAlphabetOrder,
                    requireContext()
                )
            }
        }

        binding.gamesFragmentBtnPlatformOrder.setOnClickListener {
            gamesFragmentViewModel.showDialog(gamesFragmentNavController)
        }
    }
}