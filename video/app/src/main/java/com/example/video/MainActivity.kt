package com.example.video

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.fragment.app.FragmentStatePagerAdapter
import androidx.viewpager.widget.ViewPager
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var url = ArrayList<String>()

        url.add("https://www.radiantmediaplayer.com/media/bbb-360p.mp4")
        url.add("https://ia802504.us.archive.org/13/items/SampleVideo_908/Bear.mp4")
        url.add("https://file-examples.com/wp-content/uploads/2017/04/file_example_MP4_640_3MG.mp4")


        var adapter = Adapter(supportFragmentManager)
        for(str in url){
            var frag = videoFrag(str)
            adapter.addFragments(frag)
        }

        viewPager.setPageTransformer(true,CubeTransformer())
        viewPager.offscreenPageLimit = 4
        viewPager.adapter = adapter


    }

    class Adapter(fm:FragmentManager):FragmentPagerAdapter(fm){

        var videoFrags = ArrayList<Fragment>()

        override fun getItem(position: Int): Fragment {
            return videoFrags[position]
        }

        override fun getCount(): Int {
            return videoFrags.size
        }

        fun addFragments(fragment:Fragment){
            videoFrags.add(fragment)
        }
    }

    class CubeTransformer : ViewPager.PageTransformer{
        override fun transformPage(page: View, position: Float) {
            if(position<=0){
                page.pivotX = page.width.toFloat()
            }else{
                page.pivotX = 0.0f
            }

            page.pivotY = page.height * 0.5f

            page.rotationY = 90f*position

        }

    }

}
