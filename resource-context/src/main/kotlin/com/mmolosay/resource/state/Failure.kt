package com.mmolosay.resource.state

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.util.UnknownOriginException

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
 * Represents failure, occurred while obtaining data.
 *
 * @param cause [Throwable] caught.
 * @param payload some useful data, like int code or string message.
 */
class Failure<out P>(val cause: Throwable, val payload: P? = null) :
    AbstractResourceState<Nothing>(Element) {

    companion object Element : ResourceContext.Element

    /**
     * Produces instances of [Failure] state.
     */
    interface Producer {

        /**
         * Creates new [Failure] instance.
         */
        fun <V, P> failure(
            cause: Throwable,
            payload: P?
        ): ResourceState<V> =
            Failure(cause, payload)

        /**
         * Creates new [Failure] instance without [payload].
         */
        fun <V> failure(cause: Throwable): ResourceState<V> =
            Failure<Nothing>(cause)

        /**
         * Creates new [Failure] instance without [payload].
         * [Failure.cause] will be a [UnknownOriginException] with [cause] as its message.
         */
        fun <V> failure(cause: String): ResourceState<V> =
            Failure<Nothing>(UnknownOriginException(cause))

    }
}