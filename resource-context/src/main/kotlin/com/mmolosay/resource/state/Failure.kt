package com.mmolosay.resource.state

import com.mmolosay.resource.context.ResourceContext

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
 * @param cause [Exception] caught.
 */
public class Failure(
    public val cause: Exception,
) : AbstractResourceState<Nothing>(Element) {

    public companion object Element : ResourceContext.Element

    /**
     * Produces instances of [Failure] state.
     */
    public interface Producer {

        /**
         * Creates new [Failure] out of [cause] exception.
         */
        public fun <V> failure(cause: Exception): ResourceState<V> =
            Failure(cause)

        /**
         * Creates new [Failure] out of [cause] string.
         * [Failure.cause] will be an [Exception] with [cause] for its message.
         */
        public fun <V> failure(cause: String): ResourceState<V> =
            Failure(Exception(cause))

    }
}