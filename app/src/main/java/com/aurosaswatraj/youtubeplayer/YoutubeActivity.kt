package com.aurosaswatraj.youtubeplayer

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView


    const val YOUTUBE_VIDEO_ID="8tIEVVsgHYY"
    const val PLAYLIST_ID="PLOm-rOJmMe6dJYYiqhNd5TkNvhl_o6_-R"
  private const val Tag:String="YoutubeActivity"


class YoutubeActivity : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener {

    private val DIALOG_REQUEST_CODE=1
    private val playerView by lazy {YouTubePlayerView(this)}


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_youtube)

        val layout=layoutInflater.inflate(R.layout.activity_youtube,null) as ConstraintLayout


        playerView.layoutParams=ConstraintLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT)
        layout.addView(playerView)

        setContentView(layout)

        //Step-1 Inintialization of youtubePlayerView
        playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)

    }

    override fun onInitializationSuccess(
        provider: YouTubePlayer.Provider?,
        youtubePlayer: YouTubePlayer?,
        wasRestored: Boolean)
    {
     Log.d(Tag,"onInitializationSuccess: provider is ${provider?.javaClass}")
     Log.d(Tag,"onInitializationSuccess: YoutubePlayer is ${youtubePlayer?.javaClass}")
        Toast.makeText(this,"Initialized YoutubePlayer successfully",Toast.LENGTH_SHORT).show()

        youtubePlayer?.setPlaybackEventListener(playbacklistener)
        youtubePlayer?.setPlayerStateChangeListener(playerStateChange)
        if(!wasRestored)
        {
            youtubePlayer?.loadVideo(YOUTUBE_VIDEO_ID)
        }
        else
        {
            youtubePlayer?.play()
        }

    }

    override fun onInitializationFailure(
        provider: YouTubePlayer.Provider?,
        youtubeInitializationResult: YouTubeInitializationResult?)
    {

        if (youtubeInitializationResult?.isUserRecoverableError==true)
        {
           val errorDialog=youtubeInitializationResult.getErrorDialog(this,DIALOG_REQUEST_CODE)
            errorDialog.show()
        }
        else
        {
            val errormsg="There was an error in initializing th youtubePlayer"
            Toast.makeText(this,errormsg,Toast.LENGTH_LONG).show()
        }
    }


    private val playbacklistener=object : YouTubePlayer.PlaybackEventListener{

        override fun onPlaying() {
            Toast.makeText(this@YoutubeActivity,"Video is has started Playing.>!",Toast.LENGTH_SHORT).show()
        }

        override fun onPaused() {
            Toast.makeText(this@YoutubeActivity,"Video has been paused!!",Toast.LENGTH_SHORT).show()
        }

        override fun onStopped() {
            Toast.makeText(this@YoutubeActivity,"Video stoppped...#Let's Play",Toast.LENGTH_SHORT).show()
        }

        override fun onBuffering(p0: Boolean) {
            Toast.makeText(this@YoutubeActivity,"Loading please wait...#Buffering",Toast.LENGTH_SHORT).show()
        }

        override fun onSeekTo(p0: Int) {
        Toast.makeText(this@YoutubeActivity,"Player has been seeked forward!",Toast.LENGTH_SHORT).show()
        }
    }

    private val playerStateChange=object:YouTubePlayer.PlayerStateChangeListener{

        override fun onLoading() {

            Toast.makeText(this@YoutubeActivity,"Video is loading please wait",Toast.LENGTH_SHORT).show()
        }

        override fun onLoaded(p0: String?) {

        }

        override fun onAdStarted() {
        Toast.makeText(this@YoutubeActivity,"Ad's has been implemented you will be rich",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoStarted() {
        Toast.makeText(this@YoutubeActivity,"Video Has been started",Toast.LENGTH_SHORT).show()
        }

        override fun onVideoEnded() {
        Toast.makeText(this@YoutubeActivity,"Congratulations you have successfully completed the video",Toast.LENGTH_SHORT).show()
        }

        override fun onError(p0: YouTubePlayer.ErrorReason?) {
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        Log.d(Tag,"onActivityResult called with Request Code:$requestCode and Response Code:$resultCode")
        if (requestCode==DIALOG_REQUEST_CODE)
        {
            Log.d(Tag,intent.toString())
            Log.d(Tag,intent.extras.toString())
            playerView.initialize(getString(R.string.GOOGLE_API_KEY),this)
        }

    }

}
//AIzaSyADkCmXqT8O6bkLCokMxAYBi8JmM_P3Bco


