package com.mmolosay.resource.state

import com.mmolosay.resource.util.UnknownOriginException

/*
 * ResourceState.Companion extensions
 */

// region Creators

/**
 * Returns [Empty] instance.
 */
fun <V> ResourceState.Companion.empty(): ResourceState<V> =
    Empty

/**
 * Returns [Loading] instance.
 */
fun <V> ResourceState.Companion.loading(): ResourceState<V> =
    Loading

/**
 * Returns new [Success] instance.
 */
fun <V> ResourceState.Companion.success(value: V): ResourceState<V> =
    Success(value)

/**
 * Returns new [Failure] instance.
 */
fun <V, P> ResourceState.Companion.failure(
    cause: Throwable,
    payload: P?
): ResourceState<V> =
    Failure(cause, payload)

/**
 * Returns new [Failure] instance without [Failure.payload].
 */
fun <V> ResourceState.Companion.failure(cause: Throwable): ResourceState<V> =
    Failure<Nothing>(cause)

/**
 * Returns new [Failure] instance without [Failure.payload].
 * [Failure.cause] will be a [UnknownOriginException] with [cause] as its message.
 */
fun <V> ResourceState.Companion.failure(cause: String): ResourceState<V> =
    Failure<Nothing>(UnknownOriginException(cause))

// endregion