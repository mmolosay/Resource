package com.mmolosay.resource.state

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/*
 * ResourceState extensions.
 */

/**
 * Determines whether receiver [ResourceState] is an instance of [Empty] state.
 */
public val ResourceState<*>.isEmpty: Boolean
    get() = (this is Empty)

/**
 * Determines whether receiver [ResourceState] is an instance of [Loading] state.
 */
public val ResourceState<*>.isLoading: Boolean
    get() = (this is Loading)

/**
 * Determines whether receiver [ResourceState] is an instance of [Success] state.
 */
public val ResourceState<*>.isSuccess: Boolean
    get() = (this is Success)

/**
 * Determines whether receiver [ResourceState] is an instance of [Failure] state.
 */
public val ResourceState<*>.isFailure: Boolean
    get() = (this is Failure<*>)

/**
 *
 * Returns [Success.value] if receiver [ResourceState] is an instance of [Success] state,
 * or `null` otherwise.
 *
 * @return [Success.value] or `null`
 */
public fun <V> ResourceState<V>.getOrNull(): V? =
    when (this) {
        is Success -> value
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Empty] state, or `null` otherwise.
 */
public inline fun <V, R> ResourceState<V>.ifEmpty(action: () -> R): R? =
    when (this) {
        is Empty -> action()
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Loading] state, or `null` otherwise.
 */
public inline fun <V, R> ResourceState<V>.ifLoading(action: () -> R): R? =
    when (this) {
        is Loading -> action()
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Success] state, or `null` otherwise.
 */
public inline fun <V, R> ResourceState<V>.ifSuccess(action: (value: V) -> R): R? =
    when (this) {
        is Success -> action(value)
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [ResourceState] is an instance
 * of [Failure] state, or `null` otherwise.
 */
public inline fun <V, P, R> ResourceState<V>.ifFailure(action: (cause: Throwable, payload: P?) -> R): R? =
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
public inline fun <V, P, R> ResourceState<V>.ifFailure(action: (cause: Throwable) -> R): R? =
    when (this) {
        is Failure<*> -> action(cause)
        else -> null
    }
