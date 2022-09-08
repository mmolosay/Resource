package com.mmolosay.resource.scope

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
 * Carrier of [ResourceContext].
 *
 * Implementations will also implement so-called "producers" from resource states,
 * allowed in `this` [context], so it can be used as `Kotlin` `scope` of lambdas which create
 * resource states.
 *
 * Implementing producers, which produce resource states not allowed in the [context] will
 * cause an exception in runtime.
 * See examples of scopes below as reference for implementing your own one.
 *
 * @see ReducedScope
 * @see ProgressScope
 * @see ExhaustiveScope
 */
public interface ResourceScope {

    public val context: ResourceContext
}