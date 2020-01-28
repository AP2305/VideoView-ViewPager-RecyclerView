package com.example.video

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnScrollChangeListener
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.VideoView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.SnapHelper
import kotlinx.android.synthetic.main.activity_main2.*
import kotlinx.android.synthetic.main.layout_video.view.*
import kotlin.math.abs
import kotlin.math.max

class Main2Activity : AppCompatActivity() {

//    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
//    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var url = ArrayList<String>()

        url.add("https://www.radiantmediaplayer.com/media/bbb-360p.mp4")
        url.add("https://ia802504.us.archive.org/13/items/SampleVideo_908/Bear.mp4")
        url.add("https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_640_3MG.mp4")

        viewAdapter = recyclerAdapter(url)

        recycler_view.setHasFixedSize(true)

        val snaphelper:SnapHelper = PageSnapHelper()

        snaphelper.attachToRecyclerView(recycler_view)

        val mLayoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        recycler_view.setLayoutManager(mLayoutManager)

        recycler_view.adapter = viewAdapter


    }

    class recyclerAdapter(url:ArrayList<String>) : RecyclerView.Adapter<recyclerAdapter.ViewHolder>(){

        var url = url

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val videoView = LayoutInflater.from(parent.context)
                .inflate(R.layout.layout_video,parent,false) as LinearLayout
            return ViewHolder(videoView)
        }

        override fun getItemCount(): Int {
            return url.size
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.video.videoView.setVideoPath(url[position])
            holder.video.videoView.start()
        }

        class ViewHolder(video:LinearLayout) : RecyclerView.ViewHolder(video){
            var video = video
        }

    }


    public class PageSnapHelper : LinearSnapHelper(){


        override fun findTargetSnapPosition(
            layoutManager: RecyclerView.LayoutManager?,
            velocityX: Int,
            velocityY: Int
        ): Int {

            if(!(layoutManager is RecyclerView.SmoothScroller.ScrollVectorProvider)){
                return RecyclerView.NO_POSITION
            }
            var currentView = findSnapView(layoutManager)
            if(currentView == null){
                return RecyclerView.NO_POSITION
            }
            var currentPosition = layoutManager.getPosition(currentView)
            if(currentPosition == RecyclerView.NO_POSITION){
                return RecyclerView.NO_POSITION
            }

            return currentPosition

        }



    }
}
