package io.schiar.mochannel.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import io.schiar.mochannel.R
import io.schiar.mochannel.view.viewdata.TVShowViewData

class TVShowsAdapter(
    private val tvShows: List<TVShowViewData>,
    private val onTVShowSelected: (tvShowName: String) -> Unit
) : RecyclerView.Adapter<TVShowsAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.list_item_tv_show, parent, false)

        return ViewHolder(view)
    }

    override fun getItemCount(): Int = tvShows.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.build(tvShows[position], onTVShowSelected)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun build(tvShow: TVShowViewData, onTVShowSelected: (tvShowName: String) -> Unit) {
            itemView.findViewById<TextView>(R.id.tvShowName).text = tvShow.name
            itemView.findViewById<TextView>(R.id.tvShowName).setOnClickListener {
                onTVShowSelected(tvShow.name)
            }
        }
    }
}