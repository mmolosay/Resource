package com.mmolosay.resource

import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Failure
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

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

/**
 * Invokes one of specified callbacks on receiver [Resource] depending on
 * its [Resource.state] instance.
 * If matching no concrete state, [onOther] will be invoked.
 *
 * You can create your own 'invoke' extension functions with your custom states.
 */
inline fun <V> Resource<V>.invoke(
    onEmpty: () -> Unit = {},
    onLoading: () -> Unit = {},
    onSuccess: (value: V) -> Unit = {},
    onFailure: (cause: Throwable, payload: Any?) -> Unit = { _, _ -> },
    onOther: () -> Unit = {}
) =
    when (val s = this.state) {
        is Empty -> onEmpty()
        is Loading -> onLoading()
        is Success -> onSuccess(s.value)
        is Failure<*> -> onFailure(s.cause, s.payload)
        else -> onOther()
    }
