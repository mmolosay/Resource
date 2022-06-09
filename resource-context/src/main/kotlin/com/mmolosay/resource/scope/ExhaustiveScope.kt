package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Failure
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

/**
 * [ResourceScope] with context composed of [Empty], [Loading], [Success] and [Failure] states.
 * Ideal for all kinds of data fetching and other async processes,
 * like getting data from remote or local data source.
 */
object ExhaustiveScope :
    ResourceScope,
    Empty.Producer,
    Loading.Producer,
    Success.Producer,
    Failure.Producer {

    override val context: ResourceContext =
        Empty + Loading + Success + Failure
}