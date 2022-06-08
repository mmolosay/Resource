package com.mmolosay.resource.state

import com.mmolosay.resource.AbstractResource
import com.mmolosay.resource.context.ResourceContext

/**
 * Represents success state with obtained resource [value].
 *
 * @param value obtained value.
 */
class Success<out V>(val value: V) : AbstractResource<V>(Key) {

    companion object Key : ResourceContext.Key
}