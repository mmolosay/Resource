package com.mmolosay.resource

import com.mmolosay.resource.scope.ExhaustiveScope
import com.mmolosay.resource.scope.ProgressScope
import com.mmolosay.resource.scope.ReducedScope
import com.mmolosay.resource.scope.ResourceScope
import com.mmolosay.resource.scope.ResourceStateProducer
import com.mmolosay.resource.scope.with
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

// region Invokes

/**
 * Invokes one of specified callbacks on receiver [Resource] depending on
 * its [Resource.state] instance.
 */
public inline fun <V> Resource<V, ReducedScope>.invoke(
    onEmpty: () -> Unit = {},
    onSuccess: (value: V) -> Unit = {}
): Unit =
    when (val s = this.state) {
        is Empty -> onEmpty()
        is Success -> onSuccess(s.value)
        else -> throw IllegalStateException("unreachable in runtime")
    }

/**
 * Invokes one of specified callbacks on receiver [Resource] depending on
 * its [Resource.state] instance.
 */
public inline fun <V> Resource<V, ProgressScope>.invoke(
    onEmpty: () -> Unit = {},
    onLoading: () -> Unit = {},
    onSuccess: (value: V) -> Unit = {}
): Unit =
    when (val s = this.state) {
        is Empty -> onEmpty()
        is Loading -> onLoading()
        is Success -> onSuccess(s.value)
        else -> throw IllegalStateException("unreachable in runtime")
    }

/**
 * Invokes one of specified callbacks on receiver [Resource] depending on
 * its [Resource.state] instance.
 */
public inline fun <V> Resource<V, ExhaustiveScope>.invoke(
    onEmpty: () -> Unit = {},
    onLoading: () -> Unit = {},
    onSuccess: (value: V) -> Unit = {},
    onFailure: (cause: Throwable) -> Unit = {},
): Unit =
    when (val s = this.state) {
        is Empty -> onEmpty()
        is Loading -> onLoading()
        is Success -> onSuccess(s.value)
        is Failure -> onFailure(s.cause)
        else -> throw IllegalStateException("unreachable in runtime")
    }

// endregion

/**
 * Creates new [Resource] instance of receiver's scope and state from specified [producer].
 */
public infix fun <V, S : ResourceScope> Resource<V, S>.with(producer: ResourceStateProducer<S, V>): Resource<V, S> =
    this.scope with producer
