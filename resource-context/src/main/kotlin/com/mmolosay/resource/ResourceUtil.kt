package com.mmolosay.resource

import com.mmolosay.resource.context.*
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.ResourceState

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

// region Creators

/**
 * Shorthand for [DefaultResource].
 */
fun <V> resource(state: ResourceState<V>): Resource<V> =
    DefaultResource(state)

/**
 * Scope-based version of [resource].
 */
fun <V> resource(producer: ResourceState.Companion.() -> ResourceState<V>): Resource<V> =
    DefaultResource(producer(ResourceState.Companion))

/**
 * Creates new [Resource] out of [ReducedContext] and specified [state].
 */
fun <V> ReducedResource(state: ResourceState<V> = Empty): Resource<V> =
    ResourceImpl(ReducedContext, state)

/**
 * Creates new [Resource] out of [ProgressContext] and specified [state].
 */
fun <V> ProgressResource(state: ResourceState<V> = Empty): Resource<V> =
    ResourceImpl(ProgressContext, state)

/**
 * Creates new [Resource] out of [ExhaustiveContext] and specified [state].
 */
fun <V> ExhaustiveResource(state: ResourceState<V> = Empty): Resource<V> =
    ResourceImpl(ExhaustiveContext, state)

/**
 * Creates new [Resource] out of [DefaultContext] and specified [state].
 */
fun <V> DefaultResource(state: ResourceState<V>): Resource<V> =
    ResourceImpl(DefaultContext, state)


// endregion

/**
 * Composes new [Resource] out of current one's context and specified [state].
 * Receiver [Resource.context] must have a specified [state]'s type in it,
 * otherwise exception will be thrown.
 */
infix fun <V> Resource<V>.with(state: ResourceState<V>): Resource<V> =
    ResourceImpl(this.context, state)

/**
 * Scope-based version of [Resource.with].
 */
infix fun <V> Resource<V>.with(producer: ResourceState.Companion.() -> ResourceState<V>): Resource<V> =
    ResourceImpl(this.context, producer(ResourceState.Companion))

/**
 * Composes new [Resource] out of `this` state and specified [context].
 * Specified [context] must have a receiver resource [ResourceState.type] in it,
 * otherwise exception will be thrown.
 */
infix fun <V> ResourceState<V>.with(context: ResourceContext): Resource<V> =
    ResourceImpl(context, this)

/**
 * Composes new [Resource] out of `this` context and specified [state].
 * Receiver context must have a specified [state]'s type in it,
 * otherwise exception will be thrown.
 */
infix fun <V> ResourceContext.with(state: ResourceState<V>): Resource<V> =
    ResourceImpl(this, state)

/**
 * Scope-based version of [ResourceContext.with].
 */
infix fun <V> ResourceContext.with(producer: ResourceState.Companion.() -> ResourceState<V>): Resource<V> =
    ResourceImpl(this, producer(ResourceState.Companion))