package com.example.liveapp

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import java.util.UUID
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private var liveID: String? = null
    private var name: String? = null
    private var userID: String? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStartLive = findViewById<Button>(R.id.btnStartLive)
        val liveIdIn = findViewById<EditText>(R.id.live_id_in)
        val nameJoinIn = findViewById<EditText>(R.id.name_input)

        liveIdIn.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                liveID = liveIdIn.text.toString()
                btnStartLive.text = if (liveID.isNullOrEmpty()) {
                    "Start new live"
                } else {
                    "Join a live"
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })

        btnStartLive.setOnClickListener(object: View.OnClickListener{
            override fun onClick(v: View?) {
                name = nameJoinIn.text.toString()
                if (name.isNullOrEmpty()) {
                    nameJoinIn.error = "Name is required"
                    nameJoinIn.requestFocus()
                }

                liveID = liveIdIn.text.toString()
                if (liveID.isNullOrEmpty() || liveID!!.length != 5) {
                    liveIdIn.error = "Invalid LIVE ID"
                    liveIdIn.requestFocus()

                }
                Toast.makeText(this@MainActivity,"Success",Toast.LENGTH_SHORT).show()
                startMeeting()

            }

        })

    }

     fun startMeeting() {
        Toast.makeText(this@MainActivity,"Thank",Toast.LENGTH_SHORT).show()
        var isHost: Boolean = true
        if(this.liveID!!.length==5)
        {
            isHost = false
        }
        else
        {
            liveID = generateLiveID()
        }

         userID = UUID.randomUUID().toString()

         var intent = Intent(this, LiveActivity::class.java);
         intent.putExtra("user_id", userID)
         intent.putExtra("name", name)
         intent.putExtra("live_id", liveID)
         intent.putExtra("host", isHost)
         startActivity(intent)


     }

    fun generateLiveID():String{
        var id = StringBuilder()
        while (id.length!=5)
        {
            var random = Random.nextInt(10)
            id.append(random)
        }
        return id.toString()
    }
}