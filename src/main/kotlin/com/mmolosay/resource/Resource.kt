package com.mmolosay.resource

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

    val isEmpty: Boolean
        get() = (this is Empty)

    val isLoading: Boolean
        get() = (this is Loading)

    val isSuccess: Boolean
        get() = (this is Success)

    val isFailure: Boolean
        get() = (this is Failure<*>)

    companion object
}