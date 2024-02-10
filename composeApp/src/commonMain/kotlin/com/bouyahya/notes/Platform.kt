package com.bouyahya.notes

interface Platform {
    val name: String
}

expect fun getPlatform(): Platform