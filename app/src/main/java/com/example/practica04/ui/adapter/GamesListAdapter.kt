package com.example.practica04.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.navigation.Navigation
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practica04.R
import com.example.practica04.databinding.GamesListItemBinding
import com.example.practica04.imageUrl
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo
import com.example.practica04.model.Pegi
import com.example.practica04.ui.fragment.GamesFragmentDirections
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class GamesListAdapter(private val gameLongClickListener: GameLongClickListener) :
    ListAdapter<GameBo, GamesListAdapter.GameBoViewHolder>(GamesDiffUtilCallBack) {

    interface GameLongClickListener {
        fun gameLongClick(game: GameBo)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameBoViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val gamesListItemBinding = GamesListItemBinding.inflate(layoutInflater, parent, false)
        return GameBoViewHolder(gamesListItemBinding)
    }

    override fun onBindViewHolder(holder: GamesListAdapter.GameBoViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class GameBoViewHolder(private val gamesListBinding: GamesListItemBinding) :
        RecyclerView.ViewHolder(gamesListBinding.root) {
        fun bind(item: GameBo) {
            with(gamesListBinding) {
                gameListItemImgCover.imageUrl(item.cover)
                gameListItemLabelTitle.text = item.name
                gameListItemLabelStudio.text = item.studio
                gameListItemLabelDate.text = parseDate(item.launchDate)

                gameListItemImgPlayStation.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.PLAYSTATION)
                gameListItemImgXbox.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.XBOX)
                gameListItemImgNintendo.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.NINTENDO)


                gameListItemImgPegi.setImageResource(
                    when (item.pegi) {
                        Pegi.PEGI3 -> R.drawable.pegi3
                        Pegi.PEGI7 -> R.drawable.pegi7
                        Pegi.PEGI12 -> R.drawable.pegi12
                        Pegi.PEGI16 -> R.drawable.pegi16
                        Pegi.PEGI18 -> R.drawable.pegi18
                    }
                )

                itemView.setOnLongClickListener {
                    gameLongClickListener.gameLongClick(item)
                    true
                }

                gameListItemLabelStudio.setOnClickListener {
                    val action =
                        GamesFragmentDirections.actionGamesFragmentToGamesByStudioFragment(item.studio)
                    val navController = Navigation.findNavController(itemView)
                    navController.navigate(action)
                }
            }
        }
    }

    private object GamesDiffUtilCallBack : DiffUtil.ItemCallback<GameBo>() {
        override fun areItemsTheSame(oldItem: GameBo, newItem: GameBo): Boolean =
            oldItem.id == newItem.id

        override fun areContentsTheSame(oldItem: GameBo, newItem: GameBo): Boolean =
            oldItem == newItem

    }

    private fun parseDate(date: Date): String {
        val dateFormat = SimpleDateFormat("yyyy", Locale.getDefault())
        return dateFormat.format(date)
    }
}