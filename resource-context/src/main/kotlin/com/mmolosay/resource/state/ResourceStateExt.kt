package com.mmolosay.resource.state

/*
 * ResourceState extensions.
 */

/**
 * Determines whether receiver [ResourceState] is an instance of [Empty] state.
 */
val ResourceState<*>.isEmpty: Boolean
    get() = (this is Empty)

/**
 * Determines whether receiver [ResourceState] is an instance of [Loading] state.
 */
val ResourceState<*>.isLoading: Boolean
    get() = (this is Loading)

/**
 * Determines whether receiver [ResourceState] is an instance of [Success] state.
 */
val ResourceState<*>.isSuccess: Boolean
    get() = (this is Success)

/**
 * Determines whether receiver [ResourceState] is an instance of [Failure] state.
 */
val ResourceState<*>.isFailure: Boolean
    get() = (this is Failure<*>)

/**
 *
 * Returns [Success.value] if receiver [ResourceState] is an instance of [Success] state,
 * or `null` otherwise.
 *
 * @return [Success.value] or `null`
 */
fun <V> ResourceState<V>.getOrNull(): V? =
    when (this) {
        is Success -> value
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Empty] state, or `null` otherwise.
 */
inline fun <V, R> ResourceState<V>.ifEmpty(action: () -> R): R? =
    when (this) {
        is Empty -> action()
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Loading] state, or `null` otherwise.
 */
inline fun <V, R> ResourceState<V>.ifLoading(action: () -> R): R? =
    when (this) {
        is Loading -> action()
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Success] state, or `null` otherwise.
 */
inline fun <V, R> ResourceState<V>.ifSuccess(action: (value: V) -> R): R? =
    when (this) {
        is Success -> action(value)
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Failure] state, or `null` otherwise.
 */
inline fun <V, P, R> ResourceState<V>.ifFailure(action: (cause: Throwable, payload: P?) -> R): R? =
    @Suppress("UNCHECKED_CAST")
    when (this) {
        is Failure<*> -> action(cause, payload as? P?)
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Failure] state, or `null` otherwise.
 *
 * Payload-free version of [ResourceState.ifFailure].
 */
inline fun <V, P, R> ResourceState<V>.ifFailure(action: (cause: Throwable) -> R): R? =
    when (this) {
        is Failure<*> -> action(cause)
        else -> null
    }
