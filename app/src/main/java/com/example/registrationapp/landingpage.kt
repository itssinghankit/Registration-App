package com.example.registrationapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.ktx.Firebase

class landingpage : AppCompatActivity() {

    private lateinit var firebaseAuth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_landingpage)

        var i:Int=0

        val name=findViewById<TextView>(R.id.Name)
        val branch=findViewById<TextView>(R.id.branch)
        val year=findViewById<TextView>(R.id.year)
        val gender=findViewById<TextView>(R.id.Gender)
        val email=findViewById<TextView>(R.id.email)
        val mobile=findViewById<TextView>(R.id.mobile)
        val signOut=findViewById<Button>(R.id.signout)

        val signinEmail=intent.getStringExtra("email").toString()
        val signinPassword= intent.getStringExtra("password").toString()
        var databaseEmail=""
        while(signinEmail[i]!='@')
        {
            databaseEmail=databaseEmail+signinEmail[i]
            i++
        }

        val database=FirebaseDatabase.getInstance().getReference("Users")
        database.child(databaseEmail).get().addOnSuccessListener{
            name.setText(it.child("detailName").value.toString())
            branch.setText(it.child("detailBranch").value.toString())
            year.setText(it.child("detailYear").value.toString())
            gender.setText(it.child("detailGender").value.toString())
            email.setText(it.child("detailEmail").value.toString())
            mobile.setText(it.child("detailMobile").value.toString())
        }

        signOut.setOnClickListener{
            firebaseAuth=FirebaseAuth.getInstance()
            firebaseAuth.signOut()
            val intent =Intent(this,MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

}