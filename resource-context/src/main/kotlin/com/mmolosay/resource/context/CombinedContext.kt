package com.mmolosay.resource.context

import com.mmolosay.resource.context.ResourceContext.Element

/*
 * Copyright 2022 Mikhail Malasai
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

/**
 * Left-biased set of [ResourceContext].
 */
internal class CombinedContext(
    private val left: ResourceContext,
    private val element: Element
) : ResourceContext {

    init {
        require(left != element) {
            "CombinedContext can not have similar keys"
        }
        require(!(left is CombinedContext && left.contains(element))) {
            "CombinedContext already has this key"
        }
    }

    // region ResourceContext

    override fun contains(element: Element): Boolean =
        this.element.contains(element) || left.contains(element)

    override fun <R> fold(initial: R, operation: (R, Element) -> R): R =
        operation(left.fold(initial, operation), element)

    // endregion

    // region Any

    override

    fun equals(other: Any?): Boolean =
        this === other || (other is CombinedContext && size == other.size && other.containsAll(this))

    override fun hashCode(): Int =
        left.hashCode() + element.hashCode()

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
            if (!contains(cur.element)) return false
            val next = cur.left
            if (next is CombinedContext) {
                cur = next
            } else {
                return contains(next as Element)
            }
        }
    }
}