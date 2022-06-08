package com.mmolosay.resource.state

import com.mmolosay.resource.context.ResourceContext

/**
 * Represents failure, occurred while obtaining data.
 *
 * @param cause [Throwable] caught.
 * @param payload some useful data, like int code or string message.
 */
class Failure<out P>(val cause: Throwable, val payload: P? = null) :
    AbstractResourceState<Nothing>(Element) {

    companion object Element : ResourceContext.Element
}