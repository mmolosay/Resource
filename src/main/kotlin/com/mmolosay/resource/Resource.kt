package com.mmolosay.resource

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
 * It's either [Empty], [Loading], [Success] or [Failure] instance.
 *
 * Example of usage:
 * ```
 * val observable = SomeObservable<Resource<YourData>>()
 * observable.value = Resource.Empty
 * getData { data ->
 *     observable.value = Resource.Success(data)
 * }
 * ...
 * ...
 * observable.observe { resource ->
 *     resource.fold(...)
 * }
 * ```
 *
 * @param V the type of data. Although [V] can be both `nullable` and `non-nullable` type,
 * it is __strongly recommended__ to use `non-nullable` ones, since [Success] with `null` as
 * its value may be confusing. Resource is covariant in its data type.
 */
sealed class Resource<out V> {

    /**
     * Represents empty, unset value.
     * It may was cleared, or was never set.
     */
    object Empty : Resource<Nothing>()

    /**
     * Represents loading state.
     * There is a high probability, that some other state will be set in observable future.
     */
    object Loading : Resource<Nothing>()

    /**
     * Represents success state with obtained resource [value].
     *
     * @param value obtained value.
     */
    class Success<out V>(val value: V) : Resource<V>()

    /**
     * Represents failure, occurred while obtaining data.
     *
     * @param cause [Throwable] caught.
     * @param payload some useful data, like int code or string message.
     */
    class Failure<out P>(val cause: Throwable, val payload: P? = null) : Resource<Nothing>()

    companion object
}