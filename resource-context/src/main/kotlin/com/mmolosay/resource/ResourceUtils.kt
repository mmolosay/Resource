package com.mmolosay.resource

import com.mmolosay.resource.scope.*

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
 * Utils associated with Resource.
 */

// region Type-aliases

/**
 * `typealias` for [Resource] of [ReducedScope].
 */
public typealias ReducedResource<V> = Resource<V, ReducedScope>

/**
 * `typealias` for [Resource] of [ProgressScope].
 */
public typealias ProgressResource<V> = Resource<V, ProgressScope>

/**
 * `typealias` for [Resource] of [ExhaustiveScope].
 */
public typealias ExhaustiveResource<V> = Resource<V, ExhaustiveScope>

// endregion

// region Creators

/**
 * Creates new [Resource] of [ReducedScope] with empty state by default.
 */
public fun <V> ReducedResource(
    producer: ResourceStateProducer<ReducedScope, V> = { empty() }
): ReducedResource<V> =
    ReducedScope with producer

/**
 * Creates new [Resource] of [ProgressScope] with empty state by default.
 */
public fun <V> ProgressResource(
    producer: ResourceStateProducer<ProgressScope, V> = { empty() }
): ProgressResource<V> =
    ProgressScope with producer

/**
 * Creates new [Resource] of [ExhaustiveScope] with empty state by default.
 */
public fun <V> ExhaustiveResource(
    producer: ResourceStateProducer<ExhaustiveScope, V> = { empty() }
): ExhaustiveResource<V> =
    ExhaustiveScope with producer

/**
 * Shorthand for [ExhaustiveResource] builder.
 */
public fun <V> resource(
    producer: ResourceStateProducer<ExhaustiveScope, V> = { empty() }
): ExhaustiveResource<V> =
    ExhaustiveScope with producer

/**
 * Creates new [Resource] instance.
 */
public fun <V, S : ResourceScope> Resource(
    scope: S,
    producer: ResourceStateProducer<S, V>
): Resource<V, S> =
    ResourceImpl(scope, producer(scope))

// endregion
