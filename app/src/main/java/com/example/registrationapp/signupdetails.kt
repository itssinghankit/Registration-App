package com.example.registrationapp

import android.content.Intent
import android.database.DatabaseUtils
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import com.example.registrationapp.databinding.ActivitySignupBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class signupdetails : AppCompatActivity() {
    private lateinit var detailMobile:String
    private lateinit var firebaseAuth: FirebaseAuth
    private lateinit var detailEmail:String
    private lateinit var detailPassword:String
    private lateinit var database:DatabaseReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signupdetails)

        detailMobile=intent.getStringExtra("mobileno").toString()
        detailEmail=intent.getStringExtra("email").toString()
        detailPassword=intent.getStringExtra("password").toString()

        val name=findViewById<EditText>(R.id.signupName)
        val branch=findViewById<Spinner>(R.id.branch)
        val year=findViewById<Spinner>(R.id.year)
        val gender=findViewById<RadioGroup>(R.id.genderRadio)
        val done=findViewById<Button>(R.id.done)

        var detailName:String
        var detailYear:String?=null
        var detailBranch:String?=null
        var detailGender:String?=null

        ArrayAdapter.createFromResource(this,R.array.branch,android.R.layout.simple_spinner_item).also{
            adapter->adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            branch.adapter=adapter
        }

        ArrayAdapter.createFromResource(this,R.array.year,android.R.layout.simple_spinner_item).also{
            adapter->adapter.setDropDownViewResource((android.R.layout.simple_spinner_dropdown_item))
            year.adapter=adapter
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

                                        //AFTER YEAR IS SELECTED
        year.onItemSelectedListener=object:AdapterView.OnItemSelectedListener{
            override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                detailYear=p0?.getItemAtPosition(p2).toString()
            }

            override fun onNothingSelected(p0: AdapterView<*>?) {

            }

        }

                                        //AFTER GENDER IS SELECTED
        gender.setOnCheckedChangeListener{
            RadioGroup,checkedID->
            detailGender=findViewById<RadioButton>(checkedID).text.toString()
        }
                                        //AFTER DONE BUTTON IS CLICKED
        done.setOnClickListener{
            detailName=name.text.toString()
            if(detailName.isNotEmpty()&& detailGender!!.isNotEmpty()) {

                firebaseAuth = FirebaseAuth.getInstance()
                firebaseAuth.createUserWithEmailAndPassword(detailEmail, detailPassword)
                    .addOnCompleteListener {
                        if (it.isSuccessful) {

                            database = FirebaseDatabase.getInstance().getReference("Users")
                            val UserObject = User(
                                detailName,
                                detailGender,
                                detailYear,
                                detailBranch,
                                detailMobile,
                                detailEmail
                            )
                            var databaseEmail = ""
                            var i = 0
                            //this detailEmail will give the string before @symbol in @akgec.ac.in
                            while (detailEmail[i] != '@') {
                                databaseEmail = databaseEmail + detailEmail[i]
                                i++
                            }

                            database.child(databaseEmail).setValue(UserObject)
                                .addOnCompleteListener {
                                    if (it.isSuccessful)
                                        Toast.makeText(this, "details uploaded", Toast.LENGTH_SHORT)
                                            .show()
                                    else
                                        Toast.makeText(this, "upload failed", Toast.LENGTH_SHORT)
                                            .show()
                                }

                            val intent = Intent(this, login::class.java)
                            startActivity(intent)
                            finish()
                        } else {
                            Toast.makeText(this, "User already Exists", Toast.LENGTH_SHORT).show()
                        }
                    }
            }else{
                Toast.makeText(this, "Fields cannot be Empty" +
                        "", Toast.LENGTH_SHORT).show()
            }
        }

    }
}