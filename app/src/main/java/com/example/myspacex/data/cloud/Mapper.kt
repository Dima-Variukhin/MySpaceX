package com.example.myspacex.data.cloud

interface Mapper<S, R> {
    fun map(source: S): R
}