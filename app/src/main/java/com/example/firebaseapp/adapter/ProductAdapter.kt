package com.example.firebaseapp.adapter
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.firebaseapp.R
import com.example.firebaseapp.model.Product

class ProductAdapter(private val productList: List<Product>) : RecyclerView.Adapter<ProductAdapter.ProductViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.product_item, parent, false)
        return ProductViewHolder(view)
    }

    override fun onBindViewHolder(holder: ProductViewHolder, position: Int) {
        val product = productList[position]
        holder.bind(product)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    class ProductViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val tvSKU: TextView = itemView.findViewById(R.id.tv_sku)
        private val tvNombre: TextView = itemView.findViewById(R.id.tv_nombre)
        private val tvMarca: TextView = itemView.findViewById(R.id.tv_marca)
        private val tvPrecio: TextView = itemView.findViewById(R.id.tv_precio)
        private val tvStock: TextView = itemView.findViewById(R.id.tv_stock)
        private val tvDescripcion: TextView = itemView.findViewById(R.id.tv_descripcion)

        fun bind(product: Product) {
            tvSKU.text = "SKU: ${product.sku}"
            tvNombre.text = product.nombre
            tvMarca.text = "Marca: ${product.marca}"
            tvPrecio.text = "Precio: $${product.precio}"
            tvStock.text = "Stock: ${product.stock}"
            tvDescripcion.text = product.descripcion
        }
    }
}