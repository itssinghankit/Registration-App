package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException
import com.google.firebase.auth.PhoneAuthCredential
import com.google.firebase.auth.PhoneAuthProvider

class otpverification : AppCompatActivity() {


    private lateinit var mobile:String
    private lateinit var auth:FirebaseAuth
    private lateinit var OTP:String
    private lateinit var email:String
    private lateinit var password:String
    //        val  token: PhoneAuthProvider.ForceResendingToken=getpar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)

        mobile=intent.getStringExtra("phonenumber").toString()
        OTP=intent.getStringExtra("OTP").toString()
        auth=FirebaseAuth.getInstance()

        val inputotp=findViewById<EditText>(R.id.otp)
        val verify=findViewById<Button>(R.id.verify)
verify.setOnClickListener {
    if (inputotp.text.toString().isNotEmpty()) {
        if (inputotp.text.toString().length == 6) {
            val credential = PhoneAuthProvider.getCredential(OTP!!, inputotp.text.toString())
            signInWithPhoneAuthCredential(credential)
        } else {
            Toast.makeText(this, "Enter Correct OTP", Toast.LENGTH_SHORT).show()
        }
    } else {
        Toast.makeText(this, "Enter a OTP", Toast.LENGTH_SHORT).show()
    }
}

    }
    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    email=intent.getStringExtra("email").toString()
                    password=intent.getStringExtra("password").toString()
                    // Sign in success, update UI with the signed-in user's information
                        val intent=Intent(this@otpverification,signupdetails::class.java)
                        intent.putExtra("mobileno",mobile)
                        intent.putExtra("email",email)
                        intent.putExtra("password",password)
                        startActivity(intent)
                    val user = task.result?.user
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
    }
}