@file:Suppress("unused")

import Resource.Empty
import Resource.Loading
import Resource.Success
import Resource.Failure

/*
 * Extension functions for 'Resource' companion object.
 */

// region Creators

fun <V> Resource.Companion.empty(): Resource<V> =
    Empty

fun <V> Resource.Companion.loading(): Resource<V> =
    Loading

fun <V> Resource.Companion.success(value: V): Resource<V> =
    Success(value)

fun <V, P> Resource.Companion.failure(payload: P?, cause: Throwable): Resource<V> =
    Failure(payload, cause)

fun <V> Resource.Companion.failure(cause: Throwable): Resource<V> =
    Failure(payload = null, cause)

fun <V> Resource.Companion.failure(cause: String): Resource<V> =
    Failure(payload = null, Failure.MessageException(cause))

//