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
 * Represents state of dynamically obtained data.
 *
 * @param V the type of data. Although [V] can be both `nullable` and `non-nullable` type,
 * it is __strongly recommended__ to use `non-nullable` ones, since success state with `null` as
 * its value may be confusing. State is covariant in its data type.
 */
public interface ResourceState<out V> {

    /**
     * Type of `this` state in [ResourceContext].
     */
    public val type: ResourceContext.Element

    public companion object
}