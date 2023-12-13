import kotlinx.coroutines.*
fun main(args: Array<String>) = runBlocking<Unit> {

    try {
        failedConcurrentSum()
    } catch(e: ArithmeticException) {
        println("Computation failed with ArithmeticException: $e")
    }
}
suspend fun failedConcurrentSum(): Int = coroutineScope {
    val one = async<Int> {
        println("First child starts")
        try {
            delay(Long.MAX_VALUE) // Emulates very long computation
            42
        } finally {
            println("First child was cancelled")
        }
    }

    val two = async<Int> {
        println("Second child starts")
        println("Second child throws an exception")
        throw ArithmeticException()

    }

    val three = async<Int> {
        println("Third child starts")
    }
    one.await() + two.await()
}