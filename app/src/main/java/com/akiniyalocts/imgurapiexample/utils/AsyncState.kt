package com.akiniyalocts.imgurapiexample.utils

/**
 * A class representing an network based object.
 */
sealed class AsyncState<out T> {
    companion object {
        /**
         * Helper to set metadata on a Success instance.
         * @see Success.metadata
         */
        fun <T> Success<*>.setMetadata(metadata: T) {
            this.metadata = metadata
        }

        /**
         * Helper to get metadata on a Success instance.
         * @see Success.metadata
         */
        @Suppress("UNCHECKED_CAST")
        fun <T> Success<*>.getMetadata(): T? = this.metadata as T?
    }
}

interface Incomplete

object Uninitialized : AsyncState<Nothing>(), Incomplete

object Loading : AsyncState<Nothing>(), Incomplete

data class Success<T>(val value: T) : AsyncState<T>() {
    internal var metadata: Any? = null
}

data class Fail<T>(val error: Throwable) : AsyncState<T>()
