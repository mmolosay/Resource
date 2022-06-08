package com.mmolosay.resource.scope

import com.mmolosay.resource.state.ResourceState
import com.mmolosay.resource.context.ResourceContext

/**
 * Concrete internal implementation of [Resource].
 */
internal data class ResourceImpl<V>(
    override val context: ResourceContext,
    override val state: ResourceState<V>
) : Resource<V> {

    init {
        require(context.contains(state.type)) {
            "ResourceContext does not have specified ResourceState type"
        }
    }

    override fun clone(resource: ResourceState<V>): Resource<V> =
        this.copy(state = resource)
}