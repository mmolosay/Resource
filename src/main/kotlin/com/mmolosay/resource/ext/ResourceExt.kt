package com.mmolosay.resource.ext

import com.mmolosay.resource.Resource
import com.mmolosay.resource.Resource.*

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
 * Extension functions for 'Resource' instances.
 */

val Resource<*>.isEmpty: Boolean
    get() = (this is Empty)

val Resource<*>.isLoading: Boolean
    get() = (this is Loading)

val Resource<*>.isSuccess: Boolean
    get() = (this is Success)

val Resource<*>.isFailure: Boolean
    get() = (this is Failure<*>)

/**
 *
 * Returns [Success.value] if receiver [Resource] is an instance of [Success] class,
 * or `null` otherwise.
 *
 * @return [Success.value] or `null`
 */
fun <V> Resource<V>.getOrNull(): V? =
    when (this) {
        is Success -> this.value
        else -> null
    }

/**
 * Returns result of executing specified [action], if receiver [Resource] is an instance of
 * [Empty] class, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifEmpty(action: () -> R): R? {
    return when (this) {
        is Empty -> action()
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource] is an instance of
 * [Loading] class, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifLoading(action: () -> R): R? {
    return when (this) {
        is Loading -> action()
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource] is an instance of
 * [Success] class, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifSuccess(action: (value: V) -> R): R? {
    return when (this) {
        is Success -> action(value)
        else -> null
    }
}

/**
 * Returns result of executing specified [action], if receiver [Resource] is an instance of
 * [Loading] class, or `null` otherwise
 */
inline fun <V, R> Resource<V>.ifFailure(action: () -> R): R? {
    return when (this) {
        is Loading -> action()
        else -> null
    }
}

/**
 * Invokes one of specified callbacks on receiver [Resource] depending on
 * its actual instance.
 */
inline fun <V> Resource<V>.invoke(
    onEmpty: (() -> Unit) = {},
    onLoading: (() -> Unit) = {},
    onSuccess: ((value: V) -> Unit) = {},
    onFailure: ((payload: Any?, cause: Throwable) -> Unit) = { _, _ -> }
) =
    when (this) {
        is Empty -> onEmpty()
        is Loading -> onLoading()
        is Success -> onSuccess(this.value)
        is Failure<*> -> onFailure(this.payload, this.cause)
    }