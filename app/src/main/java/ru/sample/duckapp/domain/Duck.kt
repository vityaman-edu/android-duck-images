package ru.sample.duckapp.domain

import java.net.URL

data class Duck(
    val url: String,
    val message: String,
) {
    fun url(): URL = URL(url)
}