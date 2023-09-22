package io.schiar.mochannel

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import io.schiar.mochannel.view.VideoFragment

class MainActivity: FragmentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, VideoFragment.newInstance())
                .commitNow()
        }
    }
}