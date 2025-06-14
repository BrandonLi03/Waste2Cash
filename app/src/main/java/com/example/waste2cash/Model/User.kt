package com.example.waste2cash.Model

class User (
    var userId: Int = 0,
    var username: String = "",
    var phoneNumber: String = "",
    var password: String = "",
    var address: String? = "",
    var money: Int? = 0,
    var role: String = "user",
    var category: String = "none"
)
