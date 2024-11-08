package com.example.firebaseapp

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class UpdateActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var productId: String? = null // ID de Firestore para actualizar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_update)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        toolbar.setNavigationOnClickListener {
            finish() // Cierra la actividad y regresa a la pantalla anterior
        }

        val etSKU: EditText = findViewById(R.id.et_sku)
        val btnLoadProduct: Button = findViewById(R.id.btn_load_product)
        val etNombre: EditText = findViewById(R.id.et_nombre)
        val etMarca: EditText = findViewById(R.id.et_marca)
        val etPrecio: EditText = findViewById(R.id.et_precio)
        val etStock: EditText = findViewById(R.id.et_stock)
        val etDescripcion: EditText = findViewById(R.id.et_descripcion)
        val btnUpdateProduct: Button = findViewById(R.id.btn_update_product)

        // Cargar datos del producto usando el SKU
        btnLoadProduct.setOnClickListener {
            val sku = etSKU.text.toString().trim()
            if (sku.isNotEmpty()) {
                loadProductDetailsBySKU(sku, etNombre, etMarca, etPrecio, etStock, etDescripcion)
            } else {
                Toast.makeText(this, "Por favor, ingresa un SKU de producto", Toast.LENGTH_SHORT).show()
            }
        }

        // Actualizar datos del producto
        btnUpdateProduct.setOnClickListener {
            if (productId != null) {
                updateProductDetails(productId!!, etNombre, etMarca, etPrecio, etStock, etDescripcion)
            } else {
                Toast.makeText(this, "Primero carga el producto usando el SKU", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun loadProductDetailsBySKU(sku: String, etNombre: EditText, etMarca: EditText, etPrecio: EditText, etStock: EditText, etDescripcion: EditText) {
        db.collection("productos")
            .whereEqualTo("sku", sku)
            .get()
            .addOnSuccessListener { result ->
                if (result.documents.isNotEmpty()) {
                    val document = result.documents[0]
                    productId = document.id // Guardamos el ID de Firestore para la actualización
                    etNombre.setText(document.getString("nombre"))
                    etMarca.setText(document.getString("marca"))
                    etPrecio.setText(document.getDouble("precio").toString())
                    etStock.setText(document.getLong("stock").toString())
                    etDescripcion.setText(document.getString("descripcion"))
                    Toast.makeText(this, "Producto cargado", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al buscar producto: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun updateProductDetails(productId: String, etNombre: EditText, etMarca: EditText, etPrecio: EditText, etStock: EditText, etDescripcion: EditText) {
        val updatedProduct = hashMapOf(
            "nombre" to etNombre.text.toString(),
            "marca" to etMarca.text.toString(),
            "precio" to (etPrecio.text.toString().toDoubleOrNull() ?: 0.0),
            "stock" to (etStock.text.toString().toIntOrNull() ?: 0),
            "descripcion" to etDescripcion.text.toString()
        )

        db.collection("productos").document(productId)
            .update(updatedProduct as Map<String, Any>)
            .addOnSuccessListener {
                Toast.makeText(this, "Producto actualizado exitosamente", Toast.LENGTH_SHORT).show()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al actualizar producto: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}