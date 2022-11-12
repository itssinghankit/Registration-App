package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val branch=findViewById<Spinner>(R.id.branch)
        val signup=findViewById<Button>(R.id.signupNext)

        ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item).also{
            adapter->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            branch.adapter=adapter
        }

        signup.setOnClickListener{
            val intent=Intent(this,otpverification::class.java)
            startActivity(intent)
        }

    }
}