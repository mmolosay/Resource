package com.mmolosay.resource

import com.mmolosay.resource.scope.*

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
 */
fun <V, S : ResourceScope> Resource(
    scope: S,
    producer: ResourceStateProducer<S, V>
): Resource<V, S> =
    ResourceImpl(scope, producer(scope))

// endregion
