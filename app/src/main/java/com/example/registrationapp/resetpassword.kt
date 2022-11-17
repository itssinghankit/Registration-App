package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class resetpassword : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_resetpassword)

        val send=findViewById<Button>(R.id.send)
        val email=findViewById<EditText>(R.id.email)
        val auth=FirebaseAuth.getInstance()
        send.setOnClickListener{
            if(email!=null){
                auth.sendPasswordResetEmail(email.text.toString()).addOnCompleteListener {
                    if(it.isSuccessful){
                        Toast.makeText(this, "Email is send", Toast.LENGTH_SHORT).show()
                        val intent=Intent(this,login::class.java)
                        startActivity(intent)
                    }else{
                        Toast.makeText(this, "Email cannot be sent", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                Toast.makeText(this, "please Enter your Email", Toast.LENGTH_SHORT).show()
            }
        }

    }
}