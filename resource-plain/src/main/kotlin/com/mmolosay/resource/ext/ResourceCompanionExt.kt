package com.mmolosay.resource.ext

import com.mmolosay.resource.Resource
import com.mmolosay.resource.Resource.Empty
import com.mmolosay.resource.Resource.Loading
import com.mmolosay.resource.Resource.Success
import com.mmolosay.resource.Resource.Failure
import com.mmolosay.resource.util.UnknownOriginException

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
 * Extension functions for 'Resource' companion object.
 */

// region Creators

/**
 * Returns [Empty] instance.
 */
public fun <V> Resource.Companion.empty(): Resource<V> =
    Empty

/**
 * Returns [Loading] instance.
 */
public fun <V> Resource.Companion.loading(): Resource<V> =
    Loading

/**
 * Returns new [Success] instance.
 */
public fun <V> Resource.Companion.success(value: V): Resource<V> =
    Success(value)

/**
 * Returns new [Failure] instance.
 */
public fun <V, P> Resource.Companion.failure(
    cause: Throwable,
    payload: P?
): Resource<V> =
    Failure(cause, payload)

/**
 * Returns new [Failure] instance without [Failure.payload].
 */
public fun <V> Resource.Companion.failure(cause: Throwable): Resource<V> =
    Failure<Nothing>(cause)

/**
 * Returns new [Failure] instance without [Failure.payload].
 * [Failure.cause] will be a [UnknownOriginException] with [cause] as its message.
 */
public fun <V> Resource.Companion.failure(cause: String): Resource<V> =
    Failure<Nothing>(UnknownOriginException(cause))

// endregion