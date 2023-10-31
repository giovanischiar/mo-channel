package io.schiar.mochannel.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import io.schiar.mochannel.R
import io.schiar.mochannel.view.MainActivity
import io.schiar.mochannel.view.TVShowsAdapter
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import kotlinx.coroutines.launch

class TVShowsFragment : Fragment() {
    private lateinit var viewModel: TVShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = (requireActivity() as MainActivity).tvShowsViewModel
        lifecycleScope.launch {
            viewModel.loadTVShows()
            viewModel.tvShows.collect {
                val adapter = TVShowsAdapter(it) { tvShow: String ->
                    viewModel.selectTVShow(name = tvShow)
                    val nav = Navigation.findNavController(this@TVShowsFragment.requireView())
                    nav.navigate(R.id.action_TVShowsFragment_to_TVShowFragment)
                }
                val view = view ?: return@collect
                val tvShowsRecyclerView = view.findViewById<RecyclerView>(R.id.tvShows)
                tvShowsRecyclerView.adapter = adapter
            }
        }
    }
}