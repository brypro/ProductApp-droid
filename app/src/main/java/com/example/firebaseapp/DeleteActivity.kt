package com.example.firebaseapp

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.firestore.FirebaseFirestore

class DeleteActivity : AppCompatActivity() {
    private val db = FirebaseFirestore.getInstance()
    private var productId: String? = null // ID de Firestore para eliminar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_delete)

        val toolbar: androidx.appcompat.widget.Toolbar = findViewById(R.id.toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.title = null

        toolbar.setNavigationOnClickListener {
            finish()
        }

        val etSKU: EditText = findViewById(R.id.et_sku)
        val btnSearchProduct: Button = findViewById(R.id.btn_search_product)
        val btnDeleteProduct: Button = findViewById(R.id.btn_delete_product)

        // Vistas de los detalles del producto en product_item
        val productItemView: View = findViewById(R.id.product_item_view)
        val tvSKU: TextView = productItemView.findViewById(R.id.tv_sku)
        val tvNombre: TextView = productItemView.findViewById(R.id.tv_nombre)
        val tvMarca: TextView = productItemView.findViewById(R.id.tv_marca)
        val tvPrecio: TextView = productItemView.findViewById(R.id.tv_precio)
        val tvStock: TextView = productItemView.findViewById(R.id.tv_stock)
        val tvDescripcion: TextView = productItemView.findViewById(R.id.tv_descripcion)

        // Buscar producto por SKU
        btnSearchProduct.setOnClickListener {
            val sku = etSKU.text.toString().trim()
            if (sku.isNotEmpty()) {
                searchProductBySKU(sku, tvSKU, tvNombre, tvMarca, tvPrecio, tvStock, tvDescripcion, productItemView, btnDeleteProduct)
            } else {
                Toast.makeText(this, "Por favor, ingresa un SKU de producto", Toast.LENGTH_SHORT).show()
            }
        }

        // Eliminar producto
        btnDeleteProduct.setOnClickListener {
            productId?.let {
                deleteProduct(it)
            } ?: Toast.makeText(this, "Primero busca el producto", Toast.LENGTH_SHORT).show()
        }
    }

    private fun searchProductBySKU(
        sku: String,
        tvSKU: TextView,
        tvNombre: TextView,
        tvMarca: TextView,
        tvPrecio: TextView,
        tvStock: TextView,
        tvDescripcion: TextView,
        productItemView: View,
        btnDeleteProduct: Button
    ) {
        db.collection("productos")
            .whereEqualTo("sku", sku)
            .get()
            .addOnSuccessListener { result ->
                if (result.documents.isNotEmpty()) {
                    val document = result.documents[0]
                    productId = document.id // Guardamos el ID de Firestore para la eliminación
                    // Mostramos los detalles en la vista de producto
                    tvSKU.text = "SKU: ${document.getString("sku")}"
                    tvNombre.text = document.getString("nombre")
                    tvMarca.text = "Marca: ${document.getString("marca")}"
                    tvPrecio.text = "Precio: $${document.getDouble("precio")}"
                    tvStock.text = "Stock: ${document.getLong("stock")}"
                    tvDescripcion.text = document.getString("descripcion")

                    // Mostramos la vista del producto y el botón de eliminar
                    productItemView.visibility = View.VISIBLE
                    btnDeleteProduct.visibility = View.VISIBLE
                    Toast.makeText(this, "Producto encontrado. Puedes eliminarlo.", Toast.LENGTH_SHORT).show()
                } else {
                    Toast.makeText(this, "Producto no encontrado", Toast.LENGTH_SHORT).show()
                }
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al buscar producto: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun deleteProduct(productId: String) {
        db.collection("productos").document(productId)
            .delete()
            .addOnSuccessListener {
                Toast.makeText(this, "Producto eliminado exitosamente", Toast.LENGTH_SHORT).show()
                resetFields()
            }
            .addOnFailureListener { e ->
                Toast.makeText(this, "Error al eliminar producto: ${e.message}", Toast.LENGTH_SHORT).show()
            }
    }

    private fun resetFields() {
        findViewById<EditText>(R.id.et_sku).text.clear()
        findViewById<Button>(R.id.btn_delete_product).visibility = View.GONE
        findViewById<View>(R.id.product_item_view).visibility = View.GONE
        productId = null
    }
}