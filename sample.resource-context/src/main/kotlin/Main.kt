//import com.mmolosay.resource.*
//import com.mmolosay.resource.context.ResourceContext
//import com.mmolosay.resource.scope.ReducedScope.empty
//import com.mmolosay.resource.scope.with
//import com.mmolosay.resource.state.*
//import kotlinx.coroutines.delay
//import kotlinx.coroutines.flow.MutableStateFlow
//import kotlinx.coroutines.flow.update
//import kotlinx.coroutines.launch
//import kotlinx.coroutines.runBlocking
//import kotlin.random.Random
//
//fun main() {
//    val r = resource<String> { empty() }
//    val r1 = r with { loading() }
//    val r2 = r with { success("data") }
//    println("Enter desired demonstration.")
//    println("1: default resource context")
//    println("2: custom resource context")
//    while (true) {
//        when (readLine()?.toInt() ?: 1) {
//            1 -> {
//                println("Demonstrating default resource context:")
//                defaultContextSample()
//                break
//            }
//            2 -> {
//                println("Demonstration custom resource context:")
//                customContextSample()
//                break
//            }
//            else -> println("Invalid input. Try again.")
//        }
//    }
//}
//
///**
// * Demonstrates usage of default resource context.
// */
//private fun defaultContextSample() =
//    runBlocking {
//        val flow = MutableStateFlow(resource<String> { empty() })
//        launch {
//            flow.collect { resource ->
//                resource.invoke(
//                    onEmpty = { println("empty") },
//                    onLoading = { println("loading") },
//                    onSuccess = { println("success, data=$it") },
//                    onFailure = { cause, _ -> println("failure, cause=$cause") }
//                )
//            }
//        }
//        getData(flow)
//    }
//
///**
// * Demonstrates usage of custom resource context.
// */
//private fun customContextSample() =
//    runBlocking {
//        val context = Empty + Connecting + Connected + Success
//        val flow = MutableStateFlow(context with { empty<String>() })
//        launch {
//            flow.collect { resource ->
//                resource.invoke(
//                    onEmpty = { println("empty") },
//                    onConnecting = { println("connecting") },
//                    onConnected = { println("connected after $it ms") },
//                    onSuccess = { println("success, data=$it") }
//                )
//            }
//        }
//        flow.update { it with Connecting }
//        delay(2_000L) // simulates connection is being established
//        flow.update { it with Connected(2_000) }
//        delay(1_000L) // simulates obtaining data
//        flow.update { it with { success("connected and obtained") } }
//    }
//
///**
// * Obtains data and propagates progress in specified [dest] flow.
// */
//private suspend fun getData(dest: MutableStateFlow<Resource<String>>) {
//    dest.update { it with { loading() } }
//    try {
//        getDataAsync { data ->
//            dest.update { it with { success(data) } }
//        }
//    } catch (e: Exception) {
//        dest.update { it with { failure(e) } }
//    }
//}
//
///**
// * Simulates obtaining data asynchronously.
// */
//private suspend fun getDataAsync(action: (data: String) -> Unit) {
//    delay(2_000L)
//    // randomly decide whether to return data or throw exception
//    if (Random.nextBoolean()) {
//        action("obtained data")
//    } else {
//        throw IllegalStateException("oops")
//    }
//}
//
///**
// * Custom state. Represents connecting to remote data source.
// */
//object Connecting : ResourceState<Nothing>, ResourceContext.Element {
//    override val type: ResourceContext.Element = Connecting
//}
//
///**
// * Custom state. Represents connected to remove data source state.
// */
//data class Connected(val elapsedTimeMs: Long) : AbstractResourceState<Nothing>(Element) {
//
//    companion object Element : ResourceContext.Element
//}
//
///**
// * Extension for custom resource context.
// */
//inline fun <V> Resource<V>.invoke(
//    onEmpty: () -> Unit = {},
//    onConnecting: () -> Unit = {},
//    onConnected: (elapsed: Long) -> Unit = {},
//    onSuccess: (value: V) -> Unit = {}
//) =
//    when (val s = this.state) {
//        is Empty -> onEmpty()
//        is Connecting -> onConnecting()
//        is Connected -> onConnected(s.elapsedTimeMs)
//        is Success -> onSuccess(s.value)
//        else -> throw IllegalStateException("unreachable")
//    }
