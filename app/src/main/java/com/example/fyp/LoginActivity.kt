package com.example.fyp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.example.fyp.R.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class LoginActivity : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.login)

        auth = Firebase.auth
        val tfEmail = findViewById<TextView>(R.id.tfEmail)
        val tfPassword = findViewById<TextView>(R.id.tfPassword)
        val btnLogin = findViewById<Button>(id.btnLogin)
        val btnRegister = findViewById<Button>(R.id.btnRegister)


        val database = Firebase.database
        val myRef = database.getReference("Customer")



        btnLogin.setOnClickListener(){
            val email = tfEmail.text.toString()
            val password = tfPassword.text.toString()
            login(email, password)
        }

        btnRegister.setOnClickListener (){
            val intent = Intent(this, RegisterActivity::class.java)
            startActivity(intent)

        }




    }

    private fun login(email: String, psw: String) {
        auth.signInWithEmailAndPassword(email, psw)
            .addOnSuccessListener {
                val user = auth.currentUser
                Toast.makeText(applicationContext, "Login Success", Toast.LENGTH_LONG).show()

                val intent = Intent(this, HomeActivity::class.java)
                startActivity(intent)
                finish()
            }
            .addOnFailureListener {
                Toast.makeText(applicationContext, "Login failed", Toast.LENGTH_LONG).show()
            }
    }

    fun signOut() {
        Firebase.auth.signOut()

    }
}