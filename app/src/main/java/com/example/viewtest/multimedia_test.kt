package com.example.viewtest

import android.media.MediaPlayer
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.VideoView

class multimedia_test : BaseActivity() {
    val mediaPlayer=MediaPlayer()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_multimedia_test)
        load_toolbar()
        val manager=assets
        val fd=manager.openFd("music.mp3")

        mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
        mediaPlayer.prepare()
        val play_audio=findViewById<Button>(R.id.play_audio)
        play_audio.setOnClickListener {
            if(!mediaPlayer.isPlaying){
                mediaPlayer.start()
            }
        }
        val pause_audio=findViewById<Button>(R.id.pause_audio)
        pause_audio.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.pause()
            }
        }
        val stop_audio=findViewById<Button>(R.id.stop_audio)
        stop_audio.setOnClickListener {
            if(mediaPlayer.isPlaying){
                mediaPlayer.reset()
                mediaPlayer.setDataSource(fd.fileDescriptor,fd.startOffset,fd.length)
                mediaPlayer.prepare()
            }
        }
        val uri= Uri.parse("android.resource://$packageName/${R.raw.video}")
        val videoView=findViewById<VideoView>(R.id.videoView)
        videoView.setVideoURI(uri)
        val play_vedio=findViewById<Button>(R.id.play_video)
        play_vedio.setOnClickListener {
           if(!videoView.isPlaying){
               videoView.start()
           }
        }
        val pause_video=findViewById<Button>(R.id.pause_video)
        pause_video.setOnClickListener {
            if(videoView.isPlaying){
                videoView.pause()
            }
        }
        val replay_video=findViewById<Button>(R.id.replay_video)
        replay_video.setOnClickListener {
            videoView.resume()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        findViewById<VideoView>(R.id.videoView).suspend()
        mediaPlayer.stop()
        mediaPlayer.release()
    }
}