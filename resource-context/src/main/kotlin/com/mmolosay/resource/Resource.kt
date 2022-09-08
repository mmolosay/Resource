package com.mmolosay.resource

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.scope.ResourceScope
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
 * Immutable combination of [ResourceContext] and [ResourceScope].
 *
 * Concrete implementation should check, that [state]'s type is in the [scope]'s context.
 *
 * @param V type of data, `this` resource can cary.
 * @param S scope with states producing methods, matching its context.
 */
public interface Resource<V, S : ResourceScope> {

    /**
     * Resource's current state.
     * __Must_ match [scope]'s context.
     */
    public val state: ResourceState<V>

    /**
     * Scope.
     */
    public val scope: S
}