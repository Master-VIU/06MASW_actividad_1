package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.github.ajalt.timberkt.d

class MainActivity2 : AppCompatActivity() {

    companion object{
        val RESULT_CAL = "message_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val myMessage = intent.getStringExtra(RESULT_CAL)

        d { "My message is: $myMessage" }

        findViewById<TextView>(R.id.myResult).text = myMessage
    }
}