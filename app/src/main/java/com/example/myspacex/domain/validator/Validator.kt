package com.example.myspacex.domain.validator

interface Validator<T> {
    fun isValid(source: T): Boolean?
}