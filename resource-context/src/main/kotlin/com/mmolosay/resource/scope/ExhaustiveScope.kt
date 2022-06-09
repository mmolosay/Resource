package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Failure
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

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
 * [ResourceScope] with context composed of [Empty], [Loading], [Success] and [Failure] states.
 *
 * Ideal for all kinds of data fetching and other async processes,
 * like getting data from remote or local data source.
 */
object ExhaustiveScope :
    ResourceScope,
    Empty.Producer,
    Loading.Producer,
    Success.Producer,
    Failure.Producer {

    override val context: ResourceContext =
        Empty + Loading + Success + Failure
}