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

    companion object
}