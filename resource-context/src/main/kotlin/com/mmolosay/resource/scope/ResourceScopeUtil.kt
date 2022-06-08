package com.mmolosay.resource.scope

import com.mmolosay.resource.context.*
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.ResourceState

// region Creators

/**
 * Shorthand for [DefaultResource].
 */
fun <V> resource(state: ResourceState<V>): Resource<V> =
    DefaultResource(state)

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