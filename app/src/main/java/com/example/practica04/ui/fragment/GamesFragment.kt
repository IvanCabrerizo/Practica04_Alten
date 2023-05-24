package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica04.data.mock.GamesBoMockList
import com.example.practica04.databinding.FragmentGamesBinding
import com.example.practica04.ui.adapter.GamesListAdapter
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel

class GamesFragment : Fragment() {

    private val binding by lazy { FragmentGamesBinding.inflate(layoutInflater) }
    private val gamesFragmentViewModel: GamesFragmentViewModel by lazy {
        GamesFragmentViewModel(GamesBoMockList())
    }
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

        gamesFragmentViewModel.getGames()

        gamesFragmentViewModel.gamesList.observe(viewLifecycleOwner) { gameList ->
            gamesAdapter.submitList(gameList)
        }

        binding.gamesFragmentBtnIdOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGamesId()
                selectedOrderBtn(
                    binding.gamesFragmentBtnIdOrder,
                    binding.gamesFragmentBtnAlphabetOrder,
                    requireContext()
                )
            }
        }

        binding.gamesFragmentBtnAlphabetOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGamesName()
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