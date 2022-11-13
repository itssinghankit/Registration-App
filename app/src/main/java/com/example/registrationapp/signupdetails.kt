package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import com.example.registrationapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupdetails : AppCompatActivity() {

    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupdetails)

        val branch=findViewById<Spinner>(R.id.branch)
        val signup=findViewById<Button>(R.id.done)

        ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item).also{
            adapter->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            branch.adapter=adapter
        }

        signup.setOnClickListener{
            val intent=Intent(this,otpverification::class.java)
            startActivity(intent)
        }

        /*******************************/
//        binding=ActivitySignupBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//
//        firebaseAuth = FirebaseAuth.getInstance()
//
//        binding.signupNext.setOnClickListener{
//            var email =binding.signupEmail.text.toString()
//            val pass = binding.password.text.toString()
//
//
//            if (email.isNotEmpty()&& pass.isNotEmpty())
//            {
//                firebaseAuth.createUserWithEmailAndPassword(email,pass).addOnCompleteListener{
//                    if(it.isSuccessful){
//                        val intent=Intent(this,login::class.java)
//                        startActivity(intent)
//                    }
//                    else{
//                        Toast.makeText(this, it.exception.toString(), Toast.LENGTH_SHORT).show()
//                    }
//                }
//            }
//            else{
//                Toast.makeText(this, "cannot be emptyaaaaaaaaaaa", Toast.LENGTH_SHORT).show()
//            }
//        }
        /********************************/

    }
}