package io.schiar.mochannel.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import io.schiar.mochannel.R
import io.schiar.mochannel.view.EpisodesAdapter
import io.schiar.mochannel.view.MainActivity
import io.schiar.mochannel.viewmodel.TVShowViewModel
import kotlinx.coroutines.launch

class TVShowFragment : Fragment() {
    private lateinit var viewModel: TVShowViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_show, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).tvShowViewModel
        viewModel.loadEpisodes()
        lifecycleScope.launch {
            viewModel.episodes.collect {
                val adapter = EpisodesAdapter(it) { url ->
                    viewModel.selectEpisode(url = url)
                    val nav = Navigation.findNavController(this@TVShowFragment.requireView())
                    nav.navigate(R.id.action_TVShowFragment_to_videoFragment)
                }
                val view = view ?: return@collect
                val tvShowsRecyclerView = view.findViewById<RecyclerView>(R.id.episodes)
                tvShowsRecyclerView.adapter = adapter
            }
        }

        lifecycleScope.launch {
            viewModel.currentTVShow.collect {
                val view = view ?: return@collect
                val tvShowTitle = view.findViewById<TextView>(R.id.tvShowTitle)
                tvShowTitle.text = it
            }
        }
    }
}