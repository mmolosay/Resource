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
 * Represents success state with obtained resource [value].
 *
 * @param value obtained value.
 */
public class Success<out V>(public val value: V) : AbstractResourceState<V>(Element) {

    public companion object Element : ResourceContext.Element

    /**
     * Produces instances of [Success] state.
     */
    public interface Producer {

        /**
         * Creates new [Success] instance.
         */
        public fun <V> success(value: V): ResourceState<V> =
            Success(value)
    }
}