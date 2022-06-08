package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.ResourceState

/**
 * Combination of [ResourceContext] and actual [ResourceState] instance, matching the context.
 *
 * Concrete implementation should check, that [state]'s type is in the [context].
 */
interface Resource<V> {

    /**
     * Resource's current state.
     * __Must_ match [context].
     */
    val state: ResourceState<V>

    /**
     * Context for `this` scope.
     * Determines allowed [ResourceState]s to be set.
     */
    val context: ResourceContext

    /**
     * Returns new copy of `this` resource.
     * Should not be used directly, because it does not check [context], thus
     * illegal resource may be set.
     */
    fun clone(resource: ResourceState<V>): Resource<V>
}