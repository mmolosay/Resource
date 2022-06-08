package com.mmolosay.resource.state

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext

/**
 * Represents empty, unset value.
 * It may was cleared, or was never set.
 */
// it is a key for itself
/*
 * AbstractResource can not be used as superclass, because it requires passing key, which is
 * this object itself and not initialized at the moment yet.
 */
object Empty : Resource<Nothing>, ResourceContext.Key {

    override val key: ResourceContext.Key = Empty
}