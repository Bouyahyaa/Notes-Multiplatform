package com.bouyahya.notes.core.utils

import com.bouyahya.notes.core.error.ServerException

abstract class Repository {
    protected inline fun <T> runCatching(block: () -> T): Result<T> =
        try {
            Result.Success(block())
        } catch (e: Throwable) {
            e.printStackTrace()
            when (e) {
                is ServerException -> {
                    Result.Failure(e.message ?: "")
                }

                else -> {
                    Result.Failure("Something went wrong, please try again later")
                }
            }
        }
}
