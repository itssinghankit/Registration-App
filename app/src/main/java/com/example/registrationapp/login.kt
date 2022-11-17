package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val email = findViewById<EditText>(R.id.email)
        val password = findViewById<EditText>(R.id.signinpassword)
        val login = findViewById<Button>(R.id.loginsignin)
        val forgetPassword = findViewById<TextView>(R.id.forgetpass)
        val firebaseAuth: FirebaseAuth

        firebaseAuth = FirebaseAuth.getInstance()

        //AFTER CLICKING THE LOGIN BUTTON
        login.setOnClickListener {
            if (email.text.toString().isNotEmpty() && password.text.toString().isNotEmpty()) {
                val email = email.text.toString()
                val pass = password.text.toString()

                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
                    if (it.isSuccessful) {
                        val intent = Intent(this, landingpage::class.java)
                        intent.putExtra("email", email)
                        intent.putExtra("password", pass)
                        startActivity(intent)
                    } else {
                        Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show()
                    }
                }
            } else {
                Toast.makeText(this, "cant be empty", Toast.LENGTH_SHORT).show()
            }
        }

        //AFTER CLICKING THE FORGET PASSWORD BUTTON
        forgetPassword.setOnClickListener {
            val intent = Intent(this, resetpassword::class.java)
            startActivity(intent)
        }
    }
}