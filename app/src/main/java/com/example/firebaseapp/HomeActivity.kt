package com.example.firebaseapp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth

class HomeActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        auth = FirebaseAuth.getInstance()

        // Botón de logout
        val logoutButton: ImageButton = findViewById(R.id.logout_button)
        logoutButton.setOnClickListener {
            auth.signOut()
            startActivity(Intent(this, MainActivity::class.java))
            finish()
            Toast.makeText(this, "Sesión cerrada", Toast.LENGTH_SHORT).show()
        }

        // Navegación a la actividad de Crear Producto
        val btnCreate: Button = findViewById(R.id.btn_create)
        btnCreate.setOnClickListener {
            startActivity(Intent(this, CreateActivity::class.java))
        }

        // Navegación a la actividad de Leer Producto
        val btnRead: Button = findViewById(R.id.btn_read)
        btnRead.setOnClickListener {
            startActivity(Intent(this, ReadActivity::class.java))
        }

        // Navegación a la actividad de Actualizar Producto
        val btnUpdate: Button = findViewById(R.id.btn_update)
        btnUpdate.setOnClickListener {
            startActivity(Intent(this, UpdateActivity::class.java))
        }

        // Navegación a la actividad de Eliminar Producto
        val btnDelete: Button = findViewById(R.id.btn_delete)
        btnDelete.setOnClickListener {
            startActivity(Intent(this, DeleteActivity::class.java))
        }
    }
    // Verifica si el usuario está autenticado al abrir la HomeActivity
    override fun onStart() {
        super.onStart()
        val usuarioActual = FirebaseAuth.getInstance().currentUser
        if (usuarioActual == null) {
            // Redirige al login si no está autenticado
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }


}