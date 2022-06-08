package com.mmolosay.resource.context

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

/*
 * Resource context templates.
 */

/**
 * [ResourceContext] composed of [Empty] and [Success] states.
 */
val ReducedContext = Empty + Success

/**
 * [ResourceContext] composed of [Empty] and [Success] states.
 */
val ProgressContext = Empty + Loading + Success

/**
 * [ResourceContext] composed of [Empty], [Loading], [Success] and [Failure] states.
 */
val ExhaustiveContext = Empty + Loading + Success + Failure

/**
 * Default [ResourceContext] to be used in builder functions.
 * You might want to override it with your own one.
 */
var DefaultContext = ExhaustiveContext