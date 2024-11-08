package com.example.firebaseapp.model

data class Product(
    val sku: String = "",
    val nombre: String = "",
    val marca: String = "",
    val precio: Double = 0.0,
    val stock: Int = 0,
    val descripcion: String = ""
)