package com.mmolosay.resource

import com.mmolosay.resource.state.ResourceState
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
 * Concrete internal implementation of [Resource].
 */
internal data class ResourceImpl<V>(
    override val context: ResourceContext,
    override val state: ResourceState<V>
) : Resource<V> {

    init {
        require(context.contains(state.type)) {
            "ResourceContext does not have specified ResourceState type"
        }
    }

    override fun clone(resource: ResourceState<V>): Resource<V> =
        this.copy(state = resource)
}