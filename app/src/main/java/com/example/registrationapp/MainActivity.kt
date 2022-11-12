package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val signin:Button=findViewById(R.id.signin)
        val signupButton:Button=findViewById(R.id.signup)

        signin.setOnClickListener{
            val intent=Intent(this,login::class.java)
            startActivity(intent)
        }

        signupButton.setOnClickListener{
            val intent=Intent(this,signup::class.java)
            startActivity(intent)
        }
    }
}