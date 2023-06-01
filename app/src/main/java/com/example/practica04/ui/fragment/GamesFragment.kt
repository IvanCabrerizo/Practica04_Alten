package com.example.practica04.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.NavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.practica04.R
import com.example.practica04.databinding.FragmentGamesBinding
import com.example.practica04.model.GameBo
import com.example.practica04.ui.adapter.GamesListAdapter
import com.example.practica04.ui.viewmodel.GamesFragmentViewModel
import com.google.android.material.snackbar.Snackbar

class GamesFragment : Fragment(), GamesListAdapter.GameLongClickListener {


    private val gamesFragmentViewModel: GamesFragmentViewModel by activityViewModels()
    private val binding by lazy { FragmentGamesBinding.inflate(layoutInflater) }
    private val gamesAdapter = GamesListAdapter(this)
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
            binding.gamesFragmentListGames.scrollToPosition(0)
            gamesAdapter.submitList(gameList)
        }

        gamesFragmentViewModel.getOrderDialogStart().observe(viewLifecycleOwner) { orderDialog ->
            if (orderDialog) {
                findNavController().navigate(R.id.action_gamesFragment_to_gamesFilterDialog)
            }
        }

        gamesFragmentViewModel.sortSelected.observe(viewLifecycleOwner) { sort ->
            if (sort == GamesFragmentViewModel.SortType.ID) {
                binding.gamesFragmentBtnIdOrder.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.circular_order_icon_background
                )
                binding.gamesFragmentBtnAlphabetOrder.background = null
            } else {
                binding.gamesFragmentBtnAlphabetOrder.background = ContextCompat.getDrawable(
                    requireContext(),
                    R.drawable.circular_order_icon_background
                )
                binding.gamesFragmentBtnIdOrder.background = null
            }
        }

        gamesFragmentViewModel.getGames()

        binding.gamesFragmentBtnIdOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGames(GamesFragmentViewModel.SortType.ID)
            }
        }

        binding.gamesFragmentBtnAlphabetOrder.setOnClickListener {
            with(gamesFragmentViewModel) {
                sortGames(GamesFragmentViewModel.SortType.NAME)
            }
        }

        binding.gamesFragmentBtnPlatformOrder.setOnClickListener {
            gamesFragmentViewModel.showOrderDialog()
        }
    }

    override fun gameLongClick(game: GameBo) {
        gamesFragmentViewModel.updateItemRecyclerSelected(game)
        findNavController().navigate(R.id.action_gamesFragment_to_gamesDeleteFragmentDialog)
    }

    fun snackBarRestoreGame() {
        val snackbar = Snackbar.make(requireView(), "Juego eliminado", Snackbar.LENGTH_LONG)
        snackbar.setAction("Deshacer") {
            gamesFragmentViewModel.restoreDeletedGame()
        }
        snackbar.show()
    }
}