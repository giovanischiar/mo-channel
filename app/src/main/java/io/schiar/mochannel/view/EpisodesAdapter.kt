package io.schiar.mochannel.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.schiar.mochannel.R
import io.schiar.mochannel.view.viewdata.EpisodeViewData

class EpisodesAdapter(
    private val episodes: List<EpisodeViewData>,
    private val onEpisodeSelected: (episodeName: String) -> Unit
) : RecyclerView.Adapter<EpisodesAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_episode, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = episodes.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.build(episodes[position], onEpisodeSelected)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun build(episode: EpisodeViewData, onEpisodeSelected: (episodeName: String) -> Unit) {
            itemView.findViewById<TextView>(R.id.episodeName).text = episode.name
            itemView.findViewById<TextView>(R.id.episodeName).setOnClickListener {
                onEpisodeSelected(episode.url)
            }
        }
    }
}