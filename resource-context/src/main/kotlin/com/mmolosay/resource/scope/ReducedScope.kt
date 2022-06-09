package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Success

/**
 * [ResourceScope] with context composed of [Empty] and [Success] states.
 * Ideal for cases in which you only interested in data or its absence.
 */
object ReducedScope :
    ResourceScope,
    Empty.Producer,
    Success.Producer {

    override val context: ResourceContext =
        Empty + Success
}