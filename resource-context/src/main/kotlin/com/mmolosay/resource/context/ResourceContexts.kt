package com.mmolosay.resource.context

import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Failure
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.Success

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
val DefaultContext = Empty + Loading + Success + Failure