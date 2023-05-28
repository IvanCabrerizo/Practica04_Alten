package com.example.practica04.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.practica04.R
import com.example.practica04.databinding.GamesListItemBinding
import com.example.practica04.imageUrl
import com.example.practica04.model.CompatiblePlatform
import com.example.practica04.model.GameBo

class GamesListAdapter :
    ListAdapter<GameBo, GamesListAdapter.GameBoViewHolder>(GamesDiffUtilCallBack) {

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
                gameListItemLabelDate.text = item.launchDate.toString()

                gameListItemImgPlayStation.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.PLAYSTATION)
                gameListItemImgXbox.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.XBOX)
                gameListItemImgNintendo.isVisible =
                    item.compatiblePlatform.contains(CompatiblePlatform.NINTENDO)

                when (item.pegi) {
                    3 -> gameListItemImgPegi.setImageResource(R.drawable.pegi3)
                    7 -> gameListItemImgPegi.setImageResource(R.drawable.pegi7)
                    12 -> gameListItemImgPegi.setImageResource(R.drawable.pegi12)
                    16 -> gameListItemImgPegi.setImageResource(R.drawable.pegi16)
                    18 -> gameListItemImgPegi.setImageResource(R.drawable.pegi18)
                    else -> gameListItemImgPegi.setImageDrawable(null)
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
}