package com.example.registrationapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.example.registrationapp.databinding.ActivityLoginBinding
import com.google.firebase.auth.ActionCodeResult.ActionDataKey
import com.google.firebase.auth.FirebaseAuth

class login : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        /************************/
        val button=findViewById<Button>(R.id.loginsignin)
        val element=findViewById<TextView>(R.id.emailtext)
        button.setOnClickListener{
            element.setText("hello g kaise ho")
        }

        /*******************/
//        binding=ActivityLoginBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        firebaseAuth=FirebaseAuth.getInstance()
//
//        binding.loginsignin.setOnClickListener {
//            if (binding.email.text.toString().isNotEmpty() && binding.password.text.toString()
//                    .isNotEmpty()
//            ) {
//                val email = binding.email.text.toString()
//                val pass = binding.password.text.toString()
//
//                firebaseAuth.signInWithEmailAndPassword(email, pass).addOnCompleteListener {
//                    if (it.isSuccessful) {
//                        Toast.makeText(this, "succesfiul", Toast.LENGTH_SHORT).show()
//                    } else {
//                        Toast.makeText(this, "check internet connection", Toast.LENGTH_SHORT).show()
//                    }
//                }
//            } else {
//                Toast.makeText(this, "cant be empty", Toast.LENGTH_SHORT).show()
//            }
//        }
        /*****************/

    }



}