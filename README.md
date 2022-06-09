[![Release](https://jitpack.io/v/mmolosay/resource.svg)](https://jitpack.io/#mmolosay/resource)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Resource

Handy states for dynamically obtained data.

## Table of contents

* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Artifacts](#artifacts)
    * [resource-plain](#resource-plain)
    * [resource-context](#resource-context)
* [Installation](#installation)
* [Example of usage](#example-of-usage)
* [License](#license)

------

## Problem to solve

In contemporary software development a huge number of operations and their results are asynchronous.
For instance, you're obtaining some data from remote server. Let's say, you're creating some kind
of `Observable` and observe its `value` changes, which is initially `null`. Next you trigger
some `fetchRemoteData()` and wait.

Five seconds have passed, but `value` is still `null`. What does it mean? Was there some error? Was
the result `null`? Or connection is still being established, and you have nothing to worry about?

This is where [Resource](/resource-plain/src/main/kotlin/com/mmolosay/resource/Resource.kt) kicks
in.

It represents some explicit state of dynamically obtained data. For instance, it could be `Empty`
, `Loading`, `Success` or `Failure` state, where `Success` carries obtained data and `Failure`
encapsulates exception cause and any useful payload, like error code.

## Reasons to use

1. Concept is easy to understand.
2. Implementations of `Resource` are immutable, which makes it great choice to use with such
   solutions
   like [Kotlin Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/)
   and [Android LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData).
3. Has a lot out-of-the-box features implemented with Kotlin extension functions.
3. Small source code size.
4. 100% documented.

## Artifacts

### resource-plain

`com.github.mmolosay.resource:resource-plain:VERSION`

Sealed implementation
of [Resource](/resource-plain/src/main/kotlin/com/mmolosay/resource/Resource.kt) with
only `Resource.Empty`, `Resource.Loading`, `Resource.Success` and `Resource.Failure` derived states.
Sealing makes
it [exhaustive](https://kotlinlang.org/docs/sealed-classes.html#sealed-classes-and-when-expression)
in `when` statement, thus it's easy to cover all cases of actual instance.
*Note: author recommends to
use [Resource.invoke()](/resource-plain/src/main/kotlin/com/mmolosay/resource/ext/ResourceExt.kt#L99)
extension function instead of `when` expression.*

### resource-context

`com.github.mmolosay.resource:resource-context:VERSION`

**It is a preferred flavor of `Resource` library to use.**

Philosophy of this flavor allows you to customize a context, in which your `Resource` states will
change. 

For example, you may want your resource to only have states of `Empty` and `Success` ([ReducedScope](/resource-context/src/main/kotlin/com/mmolosay/resource/scope/ReducedScope.kt)), 
when you're interested in data or its absence only. You will be given scope, in which you can create states for resource: 
it will protect you from setting state out of context. Neat, isn't it? :>

See [Example of usage](#example-of-usage) section for more details.

## Installation

Use [JitPack](https://www.jitpack.io) to add it as a dependency to your Kotlin project.
Code snippet below shows way for adding it
via [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html):

```kotlin
repositories {
    mavenCentral()
    maven("https://jitpack.io") // make sure to use jitpack repository
}

dependencies {
    implementation("com.github.mmolosay.resource:ARTIFACT:VERSION")
}
```

Where:

* `ARTIFACT` is a desired implementation. See [Artifacts](#artifacts) section for more details.
* `VERSION` is the version of desired release. It can be obtained
  on [releases](https://github.com/mmolosay/Resource/releases) page. Latest release version is
  stated at the top of this document in JitPack badge.

## Example of usage

Following **simplified** examples demonstrate a way to use `Resource` flavors
with [Kotlin Flows](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/):

### resource-plain

Full sample is in [sample.resource-plain](/sample.resource-plain/src/main/kotlin/Main.kt) module.

```kotlin
// Declare your resource flow
val flow = MutableStateFlow(Resource.empty<YourData>())
...
// Change values
fun getData() {
    flow.update { Resource.loading() }
    try {
        getDataAsync { data ->
            flow.update { Resource.success(data) }
        }
    } catch (e: SomeException) {
        flow.update { Resource.failure(e) }
    }
}
...
// Observe changes
flow.collect { resource ->
    resource.invoke(
        onEmpty = { /* update UI, like showing some message */ },
        onLoading = { /* update UI, like showing progress bar */ },
        onSuccess = { data -> /* populate data to UI */ },
        onFailure = { payload, cause -> /* display notification on UI */ }
    )
}
```

### resource-context

Full sample is in [sample.resource-context](/sample.resource-context/src/main/kotlin/Main.kt) module.

```kotlin
// Declare your resource flow
val flow = MutableStateFlow(resource<YourData>()) // empty by default
...
// Change values
fun getData() {
    flow.update { it with { loading() } }
    try {
        getDataAsync { data ->
            flow.update { it with { success(data) } }
        }
    } catch (e: SomeException) {
        flow.update { it with { failure(e) } }
    }
}
...
// Observe changes
flow.collect { resource ->
    // You can use provided extension or write your own for your custom states
    resource.invoke(
        onEmpty = { /* update UI, like showing some message */ },
        onLoading = { /* update UI, like showing progress bar */ },
        onSuccess = { data -> /* populate data to UI */ },
        onFailure = { payload, cause -> /* display notification on UI */ }
    )
}
```

## License

```text
Copyright 2022 Mikhail Malasai

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```
