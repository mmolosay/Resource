import com.mmolosay.resource.Resource
import com.mmolosay.resource.context.ResourceContext
import com.mmolosay.resource.invoke
import com.mmolosay.resource.resource
import com.mmolosay.resource.scope.ExhaustiveScope
import com.mmolosay.resource.scope.ResourceScope
import com.mmolosay.resource.scope.ResourceStateProducer
import com.mmolosay.resource.state.AbstractResourceState
import com.mmolosay.resource.state.Empty
import com.mmolosay.resource.state.Loading
import com.mmolosay.resource.state.ResourceState
import com.mmolosay.resource.with
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    println("Enter desired demonstration.")
    println("1: default resource context")
    println("2: custom resource context")
    while (true) {
        when (readLine()?.toInt() ?: 1) {
            1 -> {
                println("Demonstrating default resource context:")
                defaultContextSample()
                break
            }
            2 -> {
                println("Demonstration custom resource context:")
                customContextSample()
                break
            }
            else -> println("Invalid input. Try again.")
        }
    }
}

/**
 * Demonstrates usage of default resource context.
 */
private fun defaultContextSample() =
    runBlocking {
        val flow = MutableStateFlow(resource<String> { empty() })
        launch {
            flow.collect { resource ->
                resource.invoke(
                    onEmpty = { println("empty") },
                    onLoading = { println("loading") },
                    onSuccess = { println("success, data=$it") },
                    onFailure = { cause, _ -> println("failure, cause=$cause") }
                )
            }
        }
        getData(flow)
    }

/**
 * Demonstrates usage of custom resource context.
 */
private fun customContextSample() =
    runBlocking {
        val flow = MutableStateFlow(ReachingResource())
        launch {
            flow.collect { resource ->
                resource.invoke(
                    onEmpty = { println("empty") },
                    onLoading = { println("connecting") },
                    onConnected = { elapsed -> println("success, connected after $elapsed ms") },
                    onConnectionError = { code -> println("connection error with code $code") }
                )
            }
        }
        flow.update { it with { loading() } }
        // randomly connect successfully or get connection error
        if (Random.nextBoolean()) {
            delay(2_000L) // simulates connection is being established
            flow.update { it with { connected(after = 2_000L) } }
        } else {
            delay(10_000L) // simulates connection is being established
            flow.update { it with { connectionError(408) } } // connection timeout with corresponding code
        }
    }

/**
 * Obtains data and propagates progress in specified [dest] flow.
 */
private suspend fun getData(dest: MutableStateFlow<Resource<String, ExhaustiveScope>>) {
    dest.update { it with { loading() } }
    try {
        getDataAsync { data ->
            dest.update { it with { success(data) } }
        }
    } catch (e: Exception) {
        dest.update { it with { failure(e) } }
    }
}

/**
 * Simulates obtaining data asynchronously.
 */
private suspend fun getDataAsync(action: (data: String) -> Unit) {
    delay(2_000L)
    // randomly decide whether to return data or throw exception
    if (Random.nextBoolean()) {
        action("obtained data")
    } else {
        throw IllegalStateException("oops")
    }
}

/**
 * Custom state. Represents connected to remove data source state.
 */
data class Connected(val elapsedTimeMs: Long) : AbstractResourceState<Nothing>(Element) {

    companion object Element : ResourceContext.Element

    /**
     * Produces instances of [Connected] state.
     */
    interface Producer {

        fun connected(after: Long): ResourceState<Nothing> =
            Connected(after)
    }
}

/**
 * Custom state. Represents state of connecting failure with error [code].
 */
data class ConnectionError(val code: Int) : AbstractResourceState<Nothing>(Element) {

    companion object Element : ResourceContext.Element

    /**
     * Produces instances of [ConnectionError] state.
     */
    interface Producer {

        fun connectionError(code: Int): ResourceState<Nothing> =
            ConnectionError(code)
    }
}

/**
 * Custom scope of reaching to some remote component.
 */
object ReachingScope :
    ResourceScope,
    Empty.Producer,
    Loading.Producer,
    Connected.Producer,
    ConnectionError.Producer {

    override val context: ResourceContext =
        Empty + Loading + Connected + ConnectionError
}

/**
 * Builder function for custom scope with empty state by default.
 * It uses `Nothing` as resource's data type, because we don't want any data.
 */
fun ReachingResource(
    producer: ResourceStateProducer<ReachingScope, Nothing> = { empty() }
): Resource<Nothing, ReachingScope> =
    Resource(ReachingScope, producer)

/**
 * Extension for resource with custom scope.
 */
inline fun <V> Resource<V, ReachingScope>.invoke(
    onEmpty: () -> Unit = {},
    onLoading: () -> Unit = {},
    onConnected: (elapsed: Long) -> Unit = {},
    onConnectionError: (code: Int) -> Unit = {}
) =
    when (val s = this.state) {
        is Empty -> onEmpty()
        is Loading -> onLoading()
        is Connected -> onConnected(s.elapsedTimeMs)
        is ConnectionError -> onConnectionError(s.code)
        else -> throw IllegalStateException("unreachable in runtime")
    }
