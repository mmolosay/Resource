package com.mmolosay.resource.context

import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext.Key

/**
 * Persistent context for the [Resource].
 *
 * Represent collection (set) of allowed in `this` context [Key]s.
 * It could be a combination of other [ResourceContext] instances.
 */
interface ResourceContext {

    /**
     * Checks if the specified [key] is contained in `this` context.
     */
    fun contains(key: Key): Boolean

    /**
     * Accumulates entries of `this` context starting with [initial] value and applying [operation]
     * from left to right to current accumulator value and each element of `this` context.
     */
    fun <R> fold(initial: R, operation: (R, Key) -> R): R

    operator fun plus(context: ResourceContext): ResourceContext =
        context.fold(this) { acc, cur ->
            CombinedContext(acc, cur)
        }

    /**
     * Element of [ResourceContext].
     * An element of the resource context is a singleton context by itself.
     */
    interface Key : ResourceContext {

        // region ResourceContext

        override fun contains(key: Key): Boolean =
            this == key

        override fun <R> fold(initial: R, operation: (R, Key) -> R): R =
            operation(initial, this)

        // endregion
    }
}