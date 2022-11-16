package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcelable
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class otpverification : AppCompatActivity() {


    private lateinit var mobile:String
    private lateinit var auth:FirebaseAuth
    private lateinit var OTP:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var resendToken:PhoneAuthProvider.ForceResendingToken
    //        val  token: PhoneAuthProvider.ForceResendingToken=getpar


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_otpverification)

        mobile=intent.getStringExtra("phonenumber").toString()
        OTP=intent.getStringExtra("OTP").toString()
        resendToken=intent.getParcelableExtra("resendToken")!!

        auth=FirebaseAuth.getInstance()

        val inputotp=findViewById<EditText>(R.id.otp)
        val verify=findViewById<Button>(R.id.verify)
        val resend=findViewById<TextView>(R.id.resendOTP)

                        //after resend button is clicked
        resend.setOnClickListener{
            resendOTP()
        }

verify.setOnClickListener {
    if (inputotp.text.toString().isNotEmpty()) {
        if (inputotp.text.toString().length == 6) {
            val credential = PhoneAuthProvider.getCredential(OTP, inputotp.text.toString())
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

                        auth.signOut()      //NO NEED OF PHONE NUMBER SIGNUP AFTER OTP VERIFICATION

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
    private fun resendOTP(){
        val options = PhoneAuthOptions.newBuilder(auth)
            .setPhoneNumber(mobile)       // Phone number to verify
            .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
            .setActivity(this@otpverification)                 // Activity (for callback binding)
            .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
            .setForceResendingToken(resendToken)
            .build()
        PhoneAuthProvider.verifyPhoneNumber(options)
    }
    private val callbacks = object : PhoneAuthProvider.OnVerificationStateChangedCallbacks() {

        override fun onVerificationCompleted(credential: PhoneAuthCredential) {
            // This callback will be invoked in two situations:
            // 1 - Instant verification. In some cases the phone number can be instantly
            //     verified without needing to send or enter a verification code.
            // 2 - Auto-retrieval. On some devices Google Play services can automatically
            //     detect the incoming verification SMS and perform verification without
            //     user action.
            signInWithPhoneAuthCredential(credential)
        }

        override fun onVerificationFailed(e: FirebaseException) {
            // This callback is invoked in an invalid request for verification is made,
            // for instance if the the phone number format is not valid.

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
                /**/               Toast.makeText(this@otpverification, "Wrong Number", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(this@otpverification, "Problem from Backend", Toast.LENGTH_SHORT).show()
            }

            // Show a message and update the UI
        }

        override fun onCodeSent(
            verificationId: String,
            token: PhoneAuthProvider.ForceResendingToken
        ) {
            // The SMS verification code has been sent to the provided phone number, we
            // now need to ask the user to enter the code and then construct a credential
            // by combining the code with a verification ID.
            // Save verification ID and resending token so we can use them later
            Toast.makeText(this@otpverification, "Code is been Sent", Toast.LENGTH_SHORT).show()
        }
    }
}