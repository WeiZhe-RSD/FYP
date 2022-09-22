package com.example.fyp

import android.content.Intent
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

/*
        val database = Firebase.database
        val myRef = database.getReference("Customer")*/


        btnRegister.setOnClickListener(){
            val email = tfEmail.text.toString()
            val name = tfName.text.toString()
            val password = tfPassword.text.toString()
            val conPassword = tfConPassword.text.toString()

            register(email, password)

            //add check email availability & name empty
/*
            if(password == conPassword) {
*/
                /*val customer = Customer(email, name, password)

                myRef.child(customer.email).setValue(customer)
                    .addOnSuccessListener {
                        Toast.makeText(applicationContext, "Add successful", Toast.LENGTH_LONG)
                            .show()
                        val intent = Intent(this, LoginActivity::class.java)
                        startActivity(intent)
                    }
                    .addOnFailureListener {
                        Toast.makeText(applicationContext, "Add failed", Toast.LENGTH_LONG).show()
                    }*/
            }
        }



    private fun register(email: String, psw: String) {

        auth.createUserWithEmailAndPassword(email, psw)
            .addOnSuccessListener {
                val user = auth.currentUser
                //binding.textView.text = "Registered > ${user!!.email}"
                Toast.makeText(applicationContext, "User created", Toast.LENGTH_LONG).show()
            }
            .addOnFailureListener { e ->
                //binding.textView.text = e.message
                Toast.makeText(applicationContext, "Failed to create user", Toast.LENGTH_LONG).show()
            }
    }




}