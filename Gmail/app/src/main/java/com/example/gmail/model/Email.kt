package com.example.gmail.model

data class Email(
    val senderInitial: String,
    val senderName: String,
    val time: String,
    val subject: String,
    val summary: String
)
