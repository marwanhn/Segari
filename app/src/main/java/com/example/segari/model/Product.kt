package com.example.segari.model



data class Product (
    val id: Long,
    val image: Int,
    val price: Int,
    val title: String,
    val weight: String,
    val categoryID: Int,
    val description: String
)