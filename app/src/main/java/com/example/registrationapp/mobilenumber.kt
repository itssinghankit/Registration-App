package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import com.google.firebase.FirebaseException
import com.google.firebase.FirebaseTooManyRequestsException
import com.google.firebase.auth.*
import java.util.concurrent.TimeUnit

class mobilenumber : AppCompatActivity() {

    private lateinit var mobile:EditText
    private lateinit var send:Button
    private lateinit var auth:FirebaseAuth
    private lateinit var number:String
    private lateinit var email:String
    private lateinit var password:String
    private lateinit var progressBar:ProgressBar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mobilenumber)

        progressBar=findViewById(R.id.progressbar)
        mobile=findViewById(R.id.mobileno)
        send=findViewById(R.id.sendotp)
        auth=FirebaseAuth.getInstance()

        send.setOnClickListener{
            number=mobile.text.toString()
            if(number.isNotEmpty()){
                if(number.length==10){
                    number="+91$number"

                    progressBar.visibility= View.VISIBLE
                    send.visibility=View.INVISIBLE

                    val options = PhoneAuthOptions.newBuilder(auth)
                        .setPhoneNumber(number)       // Phone number to verify
                        .setTimeout(60L, TimeUnit.SECONDS) // Timeout and unit
                        .setActivity(this)                 // Activity (for callback binding)
                        .setCallbacks(callbacks)          // OnVerificationStateChangedCallbacks
                        .build()
                    PhoneAuthProvider.verifyPhoneNumber(options)
                }
                else{
                    Toast.makeText(this, "Enter correct Number", Toast.LENGTH_SHORT).show()
                }
            }else{
                Toast.makeText(this, "please enter a number", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signInWithPhoneAuthCredential(credential: PhoneAuthCredential) {
        auth.signInWithCredential(credential)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    // Sign in success, update UI with the signed-in user's information
                } else {
                    // Sign in failed, display a message and update the UI
                    if (task.exception is FirebaseAuthInvalidCredentialsException) {
                        // The verification code entered was invalid
                    }
                    // Update UI
                }
            }
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
            progressBar.visibility= View.INVISIBLE
            send.visibility=View.VISIBLE

            if (e is FirebaseAuthInvalidCredentialsException) {
                // Invalid request
 /**/               Toast.makeText(this@mobilenumber, "Wrong Number", Toast.LENGTH_SHORT).show()
            } else if (e is FirebaseTooManyRequestsException) {
                // The SMS quota for the project has been exceeded
                Toast.makeText(this@mobilenumber, "Problem from Backend", Toast.LENGTH_SHORT).show()
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

            email=intent.getStringExtra("email").toString()
            password=intent.getStringExtra("password").toString()

            println(token)
            val intent=Intent(this@mobilenumber,otpverification::class.java)
            intent.putExtra("OTP",verificationId)
            intent.putExtra("resendToken",token)
            intent.putExtra("phonenumber",number)
            intent.putExtra("email",email)
            intent.putExtra("password",password)
            intent.putExtra("resendToken",token)
            startActivity(intent)

        }
    }
}