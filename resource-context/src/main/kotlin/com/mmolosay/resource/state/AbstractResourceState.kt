package com.mmolosay.resource.state

import com.mmolosay.resource.context.ResourceContext

/**
 * Base class for [ResourceState] implementations.
 */
abstract class AbstractResourceState<out V>(
    override val type: ResourceContext.Element
) : ResourceState<V>