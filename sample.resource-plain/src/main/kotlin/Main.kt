import com.mmolosay.resource.Resource
import com.mmolosay.resource.ext.*
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlin.random.Random

fun main() {
    resourcePlainSample()
}

/**
 * 'resource-plain' flavor usage showcase.
 */
fun resourcePlainSample() =
    runBlocking {
        val flow = MutableStateFlow(Resource.empty<String>())
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
 * Obtains data and propagates progress in specified [dest] flow.
 */
private suspend fun getData(dest: MutableStateFlow<Resource<String>>) {
    dest.update { Resource.loading() }
    try {
        getDataAsync { data ->
            dest.update { Resource.success(data) }
        }
    } catch (e: Exception) {
        dest.update { Resource.failure(e) }
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
