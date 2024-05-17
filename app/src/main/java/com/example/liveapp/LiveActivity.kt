package com.example.liveapp

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingConfig
import com.zegocloud.uikit.prebuilt.livestreaming.ZegoUIKitPrebuiltLiveStreamingFragment

class LiveActivity : AppCompatActivity() {
    var userID: String?=null
    var name_u: String?=null
    var liveID: String?=null
    var isHost: Boolean=true



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_live)

        var liveIDText = findViewById<TextView>(R.id.live_id_textView)
        var shareBtn = findViewById<ImageView>(R.id.share_btn)

        userID = intent.getStringExtra("user_id")
        name_u =  intent.getStringExtra("name")
        liveID= intent.getStringExtra("live_id")
        isHost = intent.getBooleanExtra("host",false)

        liveIDText.setText(liveID)
        addFragment()
    }

    fun addFragment()
    {
        val appConstants = AppConstants()
        val config: ZegoUIKitPrebuiltLiveStreamingConfig = if (isHost) {
            ZegoUIKitPrebuiltLiveStreamingConfig.host()
        } else {
            ZegoUIKitPrebuiltLiveStreamingConfig.audience()
        }
        val fragment = ZegoUIKitPrebuiltLiveStreamingFragment.newInstance(
            appConstants.appId, appConstants.appSign, userID, name_u, liveID, config
        )
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commitNow()
    }


}