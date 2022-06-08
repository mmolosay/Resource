package com.mmolosay.resource.context

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext.Element

/**
 * Persistent context for the [Resource].
 *
 * Represent collection (set) of allowed in `this` context [Element]s.
 * It could be a combination of other [ResourceContext] instances.
 */
interface ResourceContext {

    /**
     * Checks if the specified [element] is contained in `this` context.
     */
    fun contains(element: Element): Boolean

    /**
     * Accumulates entries of `this` context starting with [initial] value and applying [operation]
     * from left to right to current accumulator value and each element of `this` context.
     */
    fun <R> fold(initial: R, operation: (R, Element) -> R): R

    operator fun plus(context: ResourceContext): ResourceContext =
        context.fold(this) { acc, cur ->
            CombinedContext(acc, cur)
        }

    /**
     * Element of [ResourceContext].
     * An element of the resource context is a singleton context by itself.
     */
    interface Element : ResourceContext {

        // region ResourceContext

        override fun contains(element: Element): Boolean =
            this == element

        override fun <R> fold(initial: R, operation: (R, Element) -> R): R =
            operation(initial, this)

        // endregion
    }
}