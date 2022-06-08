package com.mmolosay.resource

import com.mmolosay.resource.context.ResourceContext
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
 * Combination of [ResourceContext] and actual [ResourceState] instance, matching the context.
 *
 * Concrete implementation should check, that [state]'s type is in the [context].
 */
interface Resource<V> {

    /**
     * Resource's current state.
     * __Must_ match [context].
     */
    val state: ResourceState<V>

    /**
     * Context for `this` scope.
     * Determines allowed [ResourceState]s to be set.
     */
    val context: ResourceContext

    /**
     * Returns new copy of `this` resource.
     * Should not be used directly, because it does not check [context], thus
     * illegal resource may be set.
     */
    fun clone(resource: ResourceState<V>): Resource<V>
}