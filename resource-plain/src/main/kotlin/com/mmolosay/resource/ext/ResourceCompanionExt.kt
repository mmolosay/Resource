package com.mmolosay.resource.ext

import com.mmolosay.resource.Resource
import com.mmolosay.resource.Resource.Empty
import com.mmolosay.resource.Resource.Failure
import com.mmolosay.resource.Resource.Loading
import com.mmolosay.resource.Resource.Success

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
 * Creates new [Success] instance out of specified [value].
 */
public fun <V> Resource.Companion.success(value: V): Resource<V> =
    Success(value)

/**
 * Creates new [Failure] instance out of [cause] exception.
 */
public fun <V> Resource.Companion.failure(cause: Exception): Resource<V> =
    Failure(cause)

/**
 * Creates new [Failure] out of [cause] string.
 * [Failure.cause] will be an [Exception] with [cause] for its message.
 */
public fun <V> Resource.Companion.failure(cause: String): Resource<V> =
    Failure(Exception(cause))

// endregion
