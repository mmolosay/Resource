package com.mmolosay.resource.scope

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext

/**
 * Combination of [ResourceContext] and actual [Resource] instance matching the context.
 *
 * Concrete implementation should check, that [resource]'s key is in the [context].
 */
interface ResourceScope<V> {

    /**
     * [Resource] state.
     * __Must_ match [context].
     */
    val resource: Resource<V>

    /**
     * Context for `this` scope.
     * Determines allowed [Resource]s to be set.
     */
    val context: ResourceContext

    /**
     * Returns new copy of `this` scope.
     * Should not be used directly, because it does not check [context], thus
     * illegal resource may be set.
     */
    fun clone(resource: Resource<V>): ResourceScope<V>
}