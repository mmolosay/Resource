package com.mmolosay.resource.scope

import com.mmolosay.resource.Resource
import com.mmolosay.resource.state.ResourceState

/*
 * ResourceScope extensions.
 */

/**
 * `typealias` for lambdas, scoped to some [ResourceScope] and returning [ResourceState].
 *
 * @param S [ResourceScope]
 * @param V desired type for [ResourceState]
 */
typealias ResourceStateProducer<S, V> = S.() -> ResourceState<V>

/**
 * Creates new [Resource] instance.
 */
infix fun <V, S : ResourceScope> S.with(producer: ResourceStateProducer<S, V>): Resource<V, S> =
    Resource(this, producer)