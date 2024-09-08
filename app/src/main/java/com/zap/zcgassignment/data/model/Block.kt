package com.zap.zcgassignment.data.model

data class Block(
    val sectionType: String, // This should match the JSON structure for "sectionType"
    val items: List<Product>? = null, // Nullable, default empty or null
)