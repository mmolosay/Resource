package com.mmolosay.resource

import com.mmolosay.resource.scope.*
import com.mmolosay.resource.state.ResourceState

/*
 * Utils associated with Resource.
 */

// region Creators

/**
 * Creates new [Resource] of [ReducedScope] with empty state by default.
 */
fun <V> ReducedResource(
    producer: ResourceStateProducer<ReducedScope, V> = { empty() }
): Resource<V, ReducedScope> =
    ReducedScope with producer

/**
 * Creates new [Resource] of [ProgressScope] with empty state by default.
 */
fun <V> ProgressResource(
    producer: ResourceStateProducer<ProgressScope, V> = { empty() }
): Resource<V, ProgressScope> =
    ProgressScope with producer

/**
 * Creates new [Resource] of [ExhaustiveResource] with empty state by default.
 */
fun <V> ExhaustiveResource(
    producer: ResourceStateProducer<ExhaustiveScope, V> = { empty() }
): Resource<V, ExhaustiveScope> =
    ExhaustiveScope with producer

/**
 * Shorthand for [ExhaustiveResource] builder.
 */
fun <V> resource(
    producer: ResourceStateProducer<ExhaustiveScope, V>
): Resource<V, ExhaustiveScope> =
    ExhaustiveScope with producer

/**
 * Creates new [Resource] instance.
 *
 * Be careful with this builder function, because if specified [state] is not in [scope]'s context,
 * you will get an exception.
 * Consider using one of resource builder functions or [ResourceScope.with].
 */
fun <V, S : ResourceScope> Resource(
    scope: S,
    state: ResourceState<V>
): Resource<V, S> =
    object : AbstractResource<V, S>(scope, state) {
        override fun clone(state: ResourceState<V>): Resource<V, S> =
            Resource(scope, state)
    }

// endregion

/**
 * Creates new [Resource] instance of receiver's scope and state from specified [producer].
 */
infix fun <V, S : ResourceScope> Resource<V, S>.with(producer: ResourceStateProducer<S, V>): Resource<V, S> =
    this.scope with producer
