package com.example.wordsapp
import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText

class MailActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mail)


        val sendTo=findViewById<EditText>(R.id.input_text_mail_to)
        val subject=findViewById<EditText>(R.id.input_subject)
        val mailBody=findViewById<EditText>(R.id.input_mail_body)
        val sendButton:Button=findViewById(R.id.btn_send)

        sendButton.setOnClickListener{
            var addresses:Array<String> = arrayOf()
            val listOfMails=sendTo.text.toString().split(" ",",",";")
            Log.d("check",listOfMails.toString())
            addresses=listOfMails.toTypedArray()
            Log.d("check", "Array:${addresses.contentToString()}")

            composeEmail(addresses,subject.text.toString(), mailBody.text.toString())
        }
    }

    private fun composeEmail(address: Array<String>, subject: String,body:String) {
        val intent = Intent(Intent.ACTION_SENDTO).apply {
            data = Uri.parse("mailto:")
            putExtra(Intent.EXTRA_EMAIL, address)
            putExtra(Intent.EXTRA_SUBJECT, subject)
            putExtra(Intent.EXTRA_TEXT,body)
        }
        this.startActivity(intent)
    }
}