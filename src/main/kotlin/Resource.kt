import Resource.Empty
import Resource.Loading
import Resource.Success
import Resource.Failure

/**
 * Represents obtainable resource, that could be consumed by UI.
 * The `Resource` is either [Empty], [Loading], [Success] or [Failure] instance.
 */
sealed class Resource<out V> {

    /**
     * Represents empty, unset value. It may was cleared, or was never set.
     */
    object Empty : Resource<Nothing>()

    /**
     * Represents loading state.
     */
    object Loading : Resource<Nothing>()

    /**
     * Represents success state with obtained resource [value].
     *
     * @param value obtained value.
     */
    class Success<out V>(val value: V) : Resource<V>()

    /**
     * Represents failure, occured while obtaining resource. [payload] can be string message,
     * int code or anything else. Any [Throwable] set can be obtainded from [cause].
     *
     * @param payload some useful data.
     * @param cause [Throwable] caught.
     */
    class Failure<out P>(
        val payload: P?,
        val cause: Throwable
    ) : Resource<Nothing>() {

        class MessageException(msg: String) : Throwable(msg)
    }

    val isEmpty: Boolean
        get() = (this is Empty)

    val isLoading: Boolean
        get() = (this is Loading)

    val isSuccess: Boolean
        get() = (this is Success)

    val isFailure: Boolean
        get() = (this is Failure<*>)

    /**
     *  Maps this [Resource] by applying one of specified callbacks to it
     *  depending on its actual instance.
     */
    inline fun fold(
        onEmpty: (() -> Unit) = {},
        onLoading: (() -> Unit) = {},
        onSuccess: ((value: V) -> Unit) = {},
        onFailure: ((payload: Any?, cause: Throwable) -> Unit) = { _, _ -> }
    ) =
        when (this) {
            is Empty -> onEmpty()
            is Loading -> onLoading()
            is Success -> onSuccess(this.value)
            is Failure<*> -> onFailure(this.payload, this.cause)
        }

    inline fun <R> ifEmpty(action: () -> R): R? {
        return when (this) {
            is Empty -> action()
            else -> null
        }
    }

    inline fun <R> ifLoading(action: () -> R): R? {
        return when (this) {
            is Loading -> action()
            else -> null
        }
    }

    inline fun <R> ifSuccess(action: (value: V) -> R): R? {
        return when (this) {
            is Success -> action(value)
            else -> null
        }
    }

    companion object
}

// region Resource.Companion Extensions

fun <V> Resource.Companion.empty(): Resource<V> =
    Empty

fun <V> Resource.Companion.loading(): Resource<V> =
    Loading

fun <V> Resource.Companion.success(value: V): Resource<V> =
    Success(value)

fun <V, P> Resource.Companion.failure(payload: P?, cause: Throwable): Resource<V> =
    Failure(payload, cause)

fun <V> Resource.Companion.failure(cause: Throwable): Resource<V> =
    Failure(payload = null, cause)

fun <V> Resource.Companion.failure(cause: String): Resource<V> =
    Failure(payload = null, Failure.MessageException(cause))

// endregion

fun <V : Any> Resource<V>.getOrNull(): V? =
    when (this) {
        is Success -> this.value
        else -> null
    }