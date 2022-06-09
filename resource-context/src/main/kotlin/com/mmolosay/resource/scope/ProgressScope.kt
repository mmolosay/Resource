package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

/**
 * [ResourceScope] with context composed of [Empty], [Loading] and [Success] states.
 * Ideal for trivial order-wait-get flows with no chance of error.
 */
object ProgressScope :
    ResourceScope,
    Empty.Producer,
    Loading.Producer,
    Success.Producer {

    override val context: ResourceContext =
        Empty + Loading + Success
}