package com.example.firebaseapp

import android.annotation.SuppressLint
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapp.adapter.ProductAdapter
import com.example.firebaseapp.model.Product
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject


class ReadActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var productAdapter: ProductAdapter
    private val productList = mutableListOf<Product>()
    private val db = FirebaseFirestore.getInstance()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_read)

        // Configuración del Toolbar
        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null // Eliminamos el título predeterminado para usar el de XML

        // Acción del botón de "Volver" en el Toolbar
        toolbar.setNavigationOnClickListener {
            finish() // Cierra la actividad y regresa a la pantalla anterior
        }

        // Configuración del RecyclerView
        recyclerView = findViewById(R.id.recycler_view)
        recyclerView.layoutManager = LinearLayoutManager(this)
        productAdapter = ProductAdapter(productList)
        recyclerView.adapter = productAdapter

        // Cargar todos los productos al iniciar la actividad
        loadAllProducts()
    }

    @SuppressLint("NotifyDataSetChanged")
    private fun loadAllProducts() {
        db.collection("productos")
            .get()
            .addOnSuccessListener { result ->
                productList.clear()
                for (document in result) {
                    val product = document.toObject<Product>()
                    productList.add(product)
                }
                this.productAdapter.notifyDataSetChanged()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al cargar productos: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }
}