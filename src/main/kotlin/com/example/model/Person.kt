package com.example.model

import kotlinx.serialization.Serializable

val customerStorage = mutableListOf<Person>()
@Serializable
data class Person(
    val id: String,
    val name: String,
    val jobDesc: String,
    val price: Double,
    val avatar: String? = null
)
