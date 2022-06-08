package com.mmolosay.resource.context

import com.mmolosay.resource.context.ResourceContext.Key

/**
 * Left-biased set of [ResourceContext].
 */
internal class CombinedContext(
    private val left: ResourceContext,
    private val key: Key
) : ResourceContext {

    init {
        require(left != key) {
            "CombinedContext can not have similar keys"
        }
        require(!(left is CombinedContext && left.contains(key))) {
            "CombinedContext already has this key"
        }
    }

    // region ResourceContext

    override fun contains(key: Key): Boolean =
        this.key.contains(key) || left.contains(key)

    override fun <R> fold(initial: R, operation: (R, Key) -> R): R =
        operation(left.fold(initial, operation), key)

    // endregion

    // region Any

    override

    fun equals(other: Any?): Boolean =
        this === other || (other is CombinedContext && size == other.size && other.containsAll(this))

    override fun hashCode(): Int =
        left.hashCode() + key.hashCode()

    override fun toString(): String =
        "[" + fold("") { acc, element ->
            if (acc.isEmpty()) element.toString() else "$acc, $element"
        } + "]"

    // endregion

    private val size: Int
        get() {
            var cur = this
            var size = 2
            while (true) {
                cur = cur.left as? CombinedContext ?: return size
                size++
            }
        }

    private fun containsAll(context: CombinedContext): Boolean {
        var cur = context
        while (true) {
            if (!contains(cur.key)) return false
            val next = cur.left
            if (next is CombinedContext) {
                cur = next
            } else {
                return contains(next as Key)
            }
        }
    }
}