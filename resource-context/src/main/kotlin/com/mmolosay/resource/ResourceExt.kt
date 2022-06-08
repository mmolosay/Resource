package com.mmolosay.resource

import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Failure
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

/*
 * Resource extensions.
 */

val Resource<*>.isEmpty: Boolean
    get() = (this.state is Empty)

val Resource<*>.isLoading: Boolean
    get() = (this.state is Loading)

val Resource<*>.isSuccess: Boolean
    get() = (this.state is Success)

val Resource<*>.isFailure: Boolean
    get() = (this.state is Failure<*>)

/**
 *
 * Returns [Success.value] if receiver [Resource]'s state is a [Success], or `null` otherwise.
 *
 * @return [Success.value] or `null`
 */
fun <V> Resource<V>.getOrNull(): V? =
    when (val s = this.state) {
        is Success -> s.value
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [Resource]'s state is an instance
 * of [Empty] state, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifEmpty(action: () -> R): R? {
    return when (this.state) {
        is Empty -> action()
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource]'s state is an instance
 * of [Loading] state, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifLoading(action: () -> R): R? {
    return when (this.state) {
        is Loading -> action()
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource]'s state is an instance
 * of [Success] state, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifSuccess(action: (value: V) -> R): R? {
    return when (val s = this.state) {
        is Success -> action(s.value)
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource]'s state is an instance
 * of [Failure] state, or `null` otherwise
 */
inline fun <V, P, R> Resource<V>.ifFailure(action: (cause: Throwable, payload: P?) -> R): R? {
    return when (val s = this.state) {
        is Failure<*> -> action(s.cause, s.payload as P?)
        else -> null
    }
}