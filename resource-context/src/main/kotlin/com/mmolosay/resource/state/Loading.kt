package com.mmolosay.resource.state

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext

/**
 * Represents loading state.
 * There is a high probability, that some other state will be set in observable future.
 */
// it is a key for itself
/*
 * AbstractResource can not be used as superclass, because it requires passing key, which is
 * this object itself and not initialized at the moment yet.
 */
object Loading : Resource<Nothing>, ResourceContext.Element {

    override val type: ResourceContext.Element = Loading
}