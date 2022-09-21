package com.example.fyp

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class RegisterActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        auth = Firebase.auth

        val tfName = findViewById<TextView>(R.id.tfName)
        val tfEmail = findViewById<TextView>(R.id.tfEmail)
        val tfPassword = findViewById<TextView>(R.id.tfPassword)
        val tfConPassword = findViewById<TextView>(R.id.tfConPassword)
        val btnRegister = findViewById<Button>(R.id.btnRegister)


        val database = Firebase.database
        val myRef = database.getReference("Customer")


        btnRegister.setOnClickListener(){
            val email = tfEmail.text.toString()
            val name = tfName.text.toString()
            val password = tfPassword.text.toString()
            val conPassword = tfConPassword.text.toString()


            //add check email availability & name empty
            if(password == conPassword) {
                val customer = Customer(email, name, password)

                myRef.child(customer.email).setValue(customer)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Add successful", Toast.LENGTH_LONG)
                            .show()
                    }
                    .addOnFailureListener {
                        Toast.makeText(applicationContext, "Add failed", Toast.LENGTH_LONG).show()
                    }
            }
        }

    }


}