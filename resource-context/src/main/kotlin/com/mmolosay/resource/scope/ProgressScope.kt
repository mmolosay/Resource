package com.mmolosay.resource.scope

import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.state.Empty
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
 * [ResourceScope] with context composed of [Empty], [Loading] and [Success] states.
 * Ideal for trivial order-wait-get flows with no chance of error.
 */
object ProgressScope :
    ResourceScope,
    Empty.Producer,
    Loading.Producer,
    Success.Producer {

    override val context: ResourceContext =
        Empty + Loading + Success
}