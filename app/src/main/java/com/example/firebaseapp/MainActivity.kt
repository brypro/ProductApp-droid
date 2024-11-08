package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {
    private lateinit var firebaseAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email : TextView = findViewById(R.id.edtEmail)
        val password : TextView = findViewById(R.id.edtPassword)
        val login : Button = findViewById(R.id.btnLogin)

        firebaseAuth = Firebase.auth

        login.setOnClickListener {
            val emailText = email.text.toString()
            val passwordText = password.text.toString()
            if(emailText.isEmpty() || passwordText.isEmpty()){
                Toast.makeText(baseContext, "Please fill all the fields.",
                    Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }
            firebaseAuth.signInWithEmailAndPassword(emailText, passwordText)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = firebaseAuth.currentUser
                        Toast.makeText(baseContext, "Authentication success.",
                            Toast.LENGTH_SHORT).show()
                        val intent = Intent(this, HomeActivity::class.java)
                        startActivity(intent)
                    } else {
                        Toast.makeText(baseContext, "Authentication failed.",
                            Toast.LENGTH_SHORT).show()
                    }
                }
        }

    }
}