package com.mmolosay.resource.context

import com.mmolosay.resource.context.ResourceContext.Element
import com.mmolosay.resource.state.ResourceState

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
 * Persistent context for the [ResourceState].
 *
 * Represents collection (set) of allowed in `this` context [Element]s.
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

    /**
     * Combines specified [context] and `this` one.
     * Result is a [ResourceContext] with [Element]s from both of them with all duplicates dropped.
     */
    operator fun plus(context: ResourceContext): ResourceContext =
        context.fold(this) { acc, cur ->
            if (acc.contains(cur)) {
                acc
            } else {
                CombinedContext(acc, cur)
            }
        }

    /**
     * Element of [ResourceContext].
     * Generally, a type of [ResourceState] which is allowed in this context.
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