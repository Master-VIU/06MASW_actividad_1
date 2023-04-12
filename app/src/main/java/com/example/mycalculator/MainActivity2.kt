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

        var myMessage = intent.getStringExtra(RESULT_CAL)
        if (myMessage != null) {
            if (myMessage.isEmpty()){
                myMessage = R.string.no_result.toString()
            }
        } else {
            myMessage = R.string.invalid_result.toString()
        }

        d { "My message is: $myMessage" }

        findViewById<TextView>(R.id.myResult).text = myMessage
        findViewById<Button>(R.id.share).setOnClickListener {
            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, myMessage)
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, R.string.share_with.toString())
            startActivity(shareIntent)
        }
    }
}