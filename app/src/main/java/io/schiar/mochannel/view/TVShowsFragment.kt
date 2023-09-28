package io.schiar.mochannel.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import io.schiar.mochannel.R
import io.schiar.mochannel.viewmodel.TVShowsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.coroutines.coroutineContext

class TVShowsFragment : Fragment() {

    companion object {
        fun newInstance() = TVShowsFragment()
    }

    private lateinit var viewModel: TVShowsViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_tv_shows, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this)[TVShowsViewModel::class.java]
        runBlocking {
            viewLifecycleOwner.lifecycleScope.launch {
                viewModel.loadTVShows()

                viewModel.tvShows.collect {
                    val adapter = TVShowsAdapter(it)
                    val view = view ?: return@collect
                    val tvShowsRecyclerView = view.findViewById<RecyclerView>(R.id.tvShows)
                    tvShowsRecyclerView.adapter = adapter
                }
            }
        }
    }
}