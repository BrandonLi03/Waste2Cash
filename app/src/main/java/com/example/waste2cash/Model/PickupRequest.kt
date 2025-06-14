package com.example.waste2cash.Model

data class PickupRequest(
    val id: Int,
    val userId: Int,
    val userName: String,
    val userAddress: String?,
    val userPhoneNumber: String?,
    val categoryName: String,
    val weight: Int,
    val dateTime: String
)