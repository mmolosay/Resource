package com.mmolosay.resource.scope

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext

/**
 * Concrete internal implementation of [ResourceScope].
 */
internal data class ResourceScopeImpl<V>(
    override val context: ResourceContext,
    override val resource: Resource<V>
) : ResourceScope<V> {

    init {
        require(context.contains(resource.key)) {
            "Context does not have specified resource key"
        }
    }

    override fun clone(resource: Resource<V>): ResourceScope<V> =
        this.copy(resource = resource)
}