package com.mmolosay.resource

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
 * // TODO: update
 * Represents state of dynamically obtained data.
 * It's either [Empty], [Loading], [Success] or [Failure] instance.
 *
 * Example of usage:
 * ```
 * val observable = SomeObservable<Resource<YourData>>()
 * observable.value = Resource.com.mmolosay.resource.state.Empty
 * getData { data ->
 *     observable.value = Resource.com.mmolosay.resource.state.Success(data)
 * }
 * ...
 * ...
 * observable.observe { resource ->
 *     resource.fold(...)
 * }
 * ```
 *
 * @param V the type of data. Although [V] can be both `nullable` and `non-nullable` type,
 * it is __strongly recommended__ to use `non-nullable` ones, since success state with `null` as
 * its value may be confusing. Resource is covariant in its data type.
 */
interface Resource<out V> {

    /**
     * Type of `this` resource in [ResourceContext].
     */
    val type: ResourceContext.Element

    companion object
}