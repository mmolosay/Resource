package com.mmolosay.resource.scope

import com.mmolosay.resource.Resource
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

/*
 * ResourceScope extensions.
 */

/**
 * `typealias` for lambdas, scoped to some [ResourceScope] and returning [ResourceState].
 *
 * @param S [ResourceScope]
 * @param V desired type for [ResourceState]
 */
public typealias ResourceStateProducer<S, V> = S.() -> ResourceState<V>

/**
 * Creates new [Resource] instance.
 */
public infix fun <V, S : ResourceScope> S.with(producer: ResourceStateProducer<S, V>): Resource<V, S> =
    Resource(this, producer)