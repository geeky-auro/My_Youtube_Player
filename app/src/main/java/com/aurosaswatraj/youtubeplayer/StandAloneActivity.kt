package com.aurosaswatraj.youtubeplayer

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.google.android.youtube.player.YouTubeStandalonePlayer
import kotlinx.android.synthetic.main.activity_standalone.*

class StandAloneActivity:AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_standalone)

        btnPlayvideo.setOnClickListener(this)
        btnPlayPlaylist.setOnClickListener(this)



    }

    override fun onClick(v: View) {

        val intent=when(v.id){
            R.id.btnPlayvideo->{YouTubeStandalonePlayer.createVideoIntent(this,getString(R.string.GOOGLE_API_KEY),YOUTUBE_VIDEO_ID,0,true,false)}
            R.id.btnPlayPlaylist->{YouTubeStandalonePlayer.createPlaylistIntent(this,getString(R.string.GOOGLE_API_KEY),
                PLAYLIST_ID,0,0,true,false)}
            else->throw IllegalArgumentException("Unknown Resourse for button")
        }
        startActivity(intent)

    }
}