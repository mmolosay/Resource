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
 * Represents loading state.
 * There is a high probability, that some other state will be set in observable future.
 */
// it is a key for itself
/*
 * AbstractResource can not be used as superclass, because it requires passing key, which is
 * this object itself and not initialized at the moment yet.
 */
public object Loading : ResourceState<Nothing>, ResourceContext.Element {

    override val type: ResourceContext.Element = Loading

    /**
     * Produces instances of [Loading] state.
     */
    public interface Producer {

        /**
         * Returns [Loading] instance.
         */
        public fun <V> loading(): ResourceState<V> =
            Loading
    }
}