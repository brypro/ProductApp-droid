package com.example.firebaseapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class CreateActivity : AppCompatActivity() {
    // Instancia de Firestore
    private val db = FirebaseFirestore.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create)


        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val etSKU: EditText = findViewById(R.id.et_sku)
        val etNombre: EditText = findViewById(R.id.et_nombre)
        val etMarca: EditText = findViewById(R.id.et_marca)
        val etPrecio: EditText = findViewById(R.id.et_precio)
        val etStock: EditText = findViewById(R.id.et_stock)
        val etDescripcion: EditText = findViewById(R.id.et_descripcion)
        val btnGuardar: Button = findViewById(R.id.btn_guardar)

        btnGuardar.setOnClickListener {
            val sku = etSKU.text.toString().trim()
            val nombre = etNombre.text.toString().trim()
            val marca = etMarca.text.toString().trim()
            val precio = etPrecio.text.toString().trim()
            val stock = etStock.text.toString().trim()
            val descripcion = etDescripcion.text.toString().trim()

            if (sku.isEmpty() || nombre.isEmpty() || marca.isEmpty() || precio.isEmpty() || stock.isEmpty() || descripcion.isEmpty()) {
                Toast.makeText(this, "Por favor, completa todos los campos", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            val producto = hashMapOf(
                "sku" to sku,
                "nombre" to nombre,
                "marca" to marca,
                "precio" to (precio.toDoubleOrNull() ?: 0.0),
                "stock" to (stock.toIntOrNull() ?: 0),
                "descripcion" to descripcion
            )

            db.collection("productos")
                .add(producto)
                .addOnSuccessListener {
                    Toast.makeText(this, "Producto guardado exitosamente", Toast.LENGTH_SHORT).show()
                    // Limpiar los campos después de guardar
                    etSKU.text.clear()
                    etNombre.text.clear()
                    etMarca.text.clear()
                    etPrecio.text.clear()
                    etStock.text.clear()
                    etDescripcion.text.clear()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(this, "Error al guardar: ${e.message}", Toast.LENGTH_SHORT).show()
                }
        }
    }
}