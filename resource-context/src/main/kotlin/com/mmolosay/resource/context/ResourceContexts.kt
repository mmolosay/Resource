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
val ExhaustiveContext = Empty + Loading + Success + Failure

/**
 * Default [ResourceContext] to be used in builder functions.
 * You might want to set your own context.
 */
var DefaultContext = ExhaustiveContext