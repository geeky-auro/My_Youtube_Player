package com.aurosaswatraj.youtubeplayer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout

import kotlinx.android.synthetic.main.activity_main.*
import java.lang.IllegalArgumentException

class MainActivity : AppCompatActivity(),View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnPlaySingle.setOnClickListener(this)
        btnstandalone.setOnClickListener(this)

    }

    override fun onClick(v: View) {

        val intent=when(v.id){
            R.id.btnPlaySingle->{Intent(this,YoutubeActivity::class.java)}
            R.id.btnstandalone->{Intent(this,StandAloneActivity::class.java)}
            else ->IllegalArgumentException("Unknown Access")
        }
        startActivity(intent as Intent?)

    }
}