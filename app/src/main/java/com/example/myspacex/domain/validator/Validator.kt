package com.example.myspacex.domain.validator

import java.lang.NumberFormatException
import java.util.*

interface Validator<T> {
    fun isValid(source: T): Boolean?

    class Base : Validator<String?> {
        override fun isValid(source: String?): Boolean? {
            return if (source.isNullOrEmpty() || source.length < YEAR_LENGTH) {
                null
            } else {
                try {
                    val year = source.toInt()
                    year > MINIMUM_YEAR && year <= Calendar.getInstance().get(Calendar.YEAR)
                } catch (e: NumberFormatException) {
                    false
                }
            }
        }

        companion object {
            const val MINIMUM_YEAR = 0
            const val YEAR_LENGTH = 4
        }
    }
}