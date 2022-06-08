package com.mmolosay.resource

import com.mmolosay.resource.context.ResourceContext

/**
 * Base class for [Resource] implementations.
 */
abstract class AbstractResource<out V>(
    override val key: ResourceContext.Key
) : Resource<V>