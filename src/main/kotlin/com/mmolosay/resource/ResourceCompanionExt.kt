package com.mmolosay.resource

import com.mmolosay.resource.Resource.Empty
import com.mmolosay.resource.Resource.Loading
import com.mmolosay.resource.Resource.Success
import com.mmolosay.resource.Resource.Failure

/*
 * Extension functions for 'Resource' companion object.
 */

// region Creators

/**
 * Returns [Empty] instance.
 */
fun <V> Resource.Companion.empty(): Resource<V> =
    Empty

/**
 * Returns [Loading] instance.
 */
fun <V> Resource.Companion.loading(): Resource<V> =
    Loading

/**
 * Returns new [Success] instance.
 */
fun <V> Resource.Companion.success(value: V): Resource<V> =
    Success(value)

/**
 * Returns new [Failure] instance.
 */
fun <V, P> Resource.Companion.failure(
    cause: Throwable,
    payload: P?
): Resource<V> =
    Failure(cause, payload)

/**
 * Returns new [Failure] instance without [Failure.payload].
 */
fun <V> Resource.Companion.failure(cause: Throwable): Resource<V> =
    Failure<Nothing>(cause)

/**
 * Returns new [Failure] instance without [Failure.payload].
 * [Failure.cause] will be a [UnknownOriginException] with [cause] as its message.
 */
fun <V> Resource.Companion.failure(cause: String): Resource<V> =
    Failure<Nothing>(UnknownOriginException(cause))

// endregion