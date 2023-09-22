package io.schiar.mochannel.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class VideoViewModel: ViewModel() {
    private var url = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4"
    private val _source = MutableStateFlow(url)
    val source: StateFlow<String> = _source
}