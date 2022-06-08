package com.mmolosay.resource.state

import com.mmolosay.resource.context.ResourceContext

/**
 * Represents success state with obtained resource [value].
 *
 * @param value obtained value.
 */
class Success<out V>(val value: V) : AbstractResourceState<V>(Element) {

    companion object Element : ResourceContext.Element
}