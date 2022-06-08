package com.mmolosay.resource.scope

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.DefaultContext
import com.mmolosay.resource.context.ProgressContext
import com.mmolosay.resource.context.ReducedContext
import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty

// region Creators

/**
 * Shorthand for [DefaultScope].
 */
fun <V> scope(resource: Resource<V> = Empty): ResourceScope<V> =
    DefaultScope(resource)

/**
 * Creates new [ResourceScope] out of [ReducedContext] and specified [resource].
 */
fun <V> ReducedScope(resource: Resource<V> = Empty): ResourceScope<V> =
    ResourceScopeImpl(ReducedContext, resource)

/**
 * Creates new [ResourceScope] out of [ProgressContext] and specified [resource].
 */
fun <V> ProgressScope(resource: Resource<V> = Empty): ResourceScope<V> =
    ResourceScopeImpl(ProgressContext, resource)

/**
 * Creates new [ResourceScope] out of [DefaultContext] and specified [resource].
 */
fun <V> DefaultScope(resource: Resource<V> = Empty): ResourceScope<V> =
    ResourceScopeImpl(DefaultContext, resource)

// endregion

/**
 * Composes new [ResourceScope] out of current one's context and specified [resource].
 */
infix fun <V> ResourceScope<V>.with(resource: Resource<V>): ResourceScope<V> =
    ResourceScopeImpl(this.context, resource)

/**
 * Composes new [ResourceScope] out of `this` resource and specified [context].
 */
infix fun <V> Resource<V>.with(context: ResourceContext): ResourceScope<V> =
    ResourceScopeImpl(context, this)

/**
 * Composes new [ResourceScope] out of `this` context and specified [resource].
 */
infix fun <V> ResourceContext.with(resource: Resource<V>): ResourceScope<V> =
    ResourceScopeImpl(this, resource)