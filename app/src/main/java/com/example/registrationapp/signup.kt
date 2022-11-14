package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class signup : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        val email=findViewById<EditText>(R.id.signupEmail)
        var pass=findViewById<EditText>(R.id.signupPassword)
        val cpass=findViewById<EditText>(R.id.signupCpassword)
        val button=findViewById<Button>(R.id.signup)

        //errors
        val emailError=findViewById<TextView>(R.id.signupEmailError)
        val passError=findViewById<TextView>(R.id.signupPasswordError)
        val cPassError=findViewById<TextView>(R.id.signupCpasswordError)

        var emailFlag:Boolean=false
        var passFlag:Boolean=false
        var cPassFlag:Boolean=false

                            // for email validation
        email.setOnFocusChangeListener{
            view,hasFocus->
            if(!hasFocus){
                emailFlag= emailRegex(email.text.toString())
               if( emailFlag )
                   emailError.setText("")
                else
                    emailError.setText("Should be College Email id e.g- abc@akgec.ac.in")
            }
        }

                    //for password validation
        pass.setOnFocusChangeListener{
                view,hasFocus->
            if(!hasFocus){
                passFlag= passRegex(pass.text.toString())
                if( passFlag )
                    passError.setText("")
                else
                    passError.setText("should've atleast 8 character,Upper case,Lower case,Number,and Special character")
            }
        }

                    //for confirm password
        button.setOnClickListener{

                if(pass.text.toString()==cpass.text.toString()){
                    cPassFlag=true
                    cPassError.setText("")
                        if(cPassFlag==true&&passFlag==true&&emailFlag){
                            val intent=Intent(this,mobilenumber::class.java)
                            intent.putExtra("email",email.text.toString())
                            intent.putExtra("password",pass.text.toString())
                            startActivity(intent)
                            finish()
                        }
                }
                else {
                    cPassFlag = false
                    cPassError.setText("Both Passwords should match")
                }

        }

    }

    private fun passRegex(pass: String): Boolean {
        val pattern="^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{4,}$".toRegex()
        return pattern.matches(pass)
    }

    private fun emailRegex(email:String):Boolean{
        val pattern="[a-zA-Z0-9._-]+@akgec\\.ac\\.in".toRegex()
        return pattern.matches(email)
    }
}