package com.example.video

import android.content.Context
import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.fragment_video.*
import kotlinx.android.synthetic.main.fragment_video.view.*

class videoFrag(url:String) : Fragment() {

    var url = url

    lateinit var rootView: View


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onStart() {

        rootView.video.start()

        super.onStart()
    }

    override fun onPause() {
        rootView.video.pause()
        super.onPause()
    }

    override fun onDestroy() {
        rootView.video.stopPlayback()
        super.onDestroy()
    }

    override fun onStop() {
        rootView.video.stopPlayback()

        super.onStop()
    }

    override fun onResume() {

        rootView.video.resume()
        super.onResume()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        rootView =inflater.inflate(R.layout.fragment_video, container, false)

        rootView.video.setVideoPath(url)

            rootView.video.start()


//        rootView.video.pause()

        return rootView

    }

    override fun setUserVisibleHint(isVisibleToUser: Boolean) {
        super.setUserVisibleHint(isVisibleToUser)
        if(::rootView.isInitialized) {
            if (!isVisibleToUser) {
                rootView.video.pause()
            }
            if (isVisibleToUser) {
                rootView.video.start()
            }
        }
    }
}
