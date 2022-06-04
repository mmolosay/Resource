[![Release](https://jitpack.io/v/mmolosay/resource.svg)](https://jitpack.io/#mmolosay/resource)
[![License](https://img.shields.io/badge/license-Apache%20License%202.0-blue.svg?style=flat)](https://www.apache.org/licenses/LICENSE-2.0)

# Resource
Handy states for dynamically obtained data.

## Table of contents
* [Problem to solve](#problem-to-solve)
* [Reasons to use](#reasons-to-use)
* [Installation](#installation)
* [Example of usage](#example-of-usage)
* [License](#license)

------

## Problem to solve
In contemporary software development a huge number of operations and their results are asynchronous.
For instance, you're obtaining some data from remote server. Lets say, you're creating some kind of `Observable` and observe its `value` changes, which is initially `null`. Next you trigger some `fetchRemoteData()` and wait.

Five seconds have passed, but `value` is still `null`. What does it mean? Was there some error? Was the result `null`? Or connection is still being established and you have nothing to worry about?

This is where [Resource](/src/main/kotlin/com.mmolosay/resource/Resource.kt) kisks in.
It represents some explicit state of dynamically obtained data. It could be either `Empty`, `Loading`, `Success` or `Failure` one, where `Success` cares obtained data and `Failure` incapsulates occurred exception and some usefull payload.

## Reasons to use
1. Concept is easy to understand.
2. [Resource](/src/main/kotlin/com.mmolosay/resource/Resource.kt) is immutable, which makes it great choice to use with such concepts like [Kotlin Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/) and [Android LiveData](https://developer.android.com/reference/androidx/lifecycle/LiveData).
3. Has a lot out-of-the-box features implemented with Kotlin extension functions.
3. Small source code size.
4. 100% documented.

## Installation
Use [JitPack](https://www.jitpack.io) to add it as a dependency to your Kotlin project.
Code snippet below shows way for adding it via [Gradle DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html):
```kotlin
repositories {
    mavenCentral()
    maven { setUrl("https://jitpack.io") }
}

dependencies {
    implementation("com.github.mmolosay:resource:VERSION")
}
```
Where `VERSION` is the version of desired release. It can be obtained on [releases](https://github.com/mmolosay/Resource/releases) page. Lattest release version is stated at the top of this document in JitPack badge.

## Example of usage
Following example demonstrates a way to use `Resource` with [Kotlin Flows](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/-flow/):
```kotlin
val flow = MutableStateFlow(Resource.empty<YourData>())
fetchYourData(flow) // fetches data and puts it in specified flow
flow.collect { resource ->
    resource.invoke(
        onEmpty = { /* update UI, like showing some message */ },
        onLoading = { /* update UI, like showing progress bar */ }. 
        onSuccess = { data -> /* populate data to UI */ }
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
