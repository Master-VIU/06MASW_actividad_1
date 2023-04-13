package com.example.mycalculator

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.github.ajalt.timberkt.d

class ShareResult : AppCompatActivity() {

    companion object{
        val RESULT_CAL = "message_key"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        var myMessage = trimTrailingZero(intent.getStringExtra(RESULT_CAL))
        if (myMessage != null) {
            if (myMessage.isEmpty()){
                myMessage = R.string.no_result.toString()
            }
        } else {
            myMessage = R.string.invalid_result.toString()
        }

        d { "El resultado es: $myMessage" }

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

    private fun trimTrailingZero(value: String?): String? {
        return if (!value.isNullOrEmpty()) {
            if (value.indexOf(".") < 0) {
                value
            } else {
                value.replace("0*$".toRegex(), "").replace("\\.$".toRegex(), "")
            }
        } else {
            value
        }
    }
}