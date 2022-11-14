package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.registrationapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth

class signupdetails : AppCompatActivity() {
    private lateinit var mobile:String
    private lateinit var binding: ActivitySignupBinding
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var email:String
    private lateinit var password:String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupdetails)

        mobile=intent.getStringExtra("phonenumber").toString()
        email=intent.getStringExtra("email").toString()
        password=intent.getStringExtra("password").toString()

        val name=findViewById<EditText>(R.id.signupName)
        val branch=findViewById<Spinner>(R.id.branch)
        val year=findViewById<EditText>(R.id.year)
        val gender=findViewById<RadioGroup>(R.id.genderRadio)
        val done=findViewById<Button>(R.id.done)


        var detailName:String
        var detailYear:String
        var detailBranch:String
        var detailGender:String

        ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item).also{
            adapter->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            branch.adapter=adapter
        }

                                        //AFTER BRANCH IS SELECTED
        branch.onItemSelectedListener= object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                detailBranch=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {
                Toast.makeText(this@signupdetails, "Select a Branch", Toast.LENGTH_SHORT).show()
            }

        }

        done.setOnClickListener{
             detailName=name.text.toString()
             detailYear=year.text.toString()

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