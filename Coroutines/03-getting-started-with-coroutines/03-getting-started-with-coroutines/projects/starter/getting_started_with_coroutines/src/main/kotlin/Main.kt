import kotlinx.coroutines.*
import kotlin.coroutines.CoroutineContext

//=====================================================================================
//On page 56 it's claimed that following code if missing Thread.sleep(1000) statement
//may print some output, all output or none output
//I do however think this is incorrect and the following code will always print
//All it's output, although in possibly different order every time
//======================================================================================
/**
fun main() {
  (1..10000).forEach {
    GlobalScope.launch {
      val threadName = Thread.currentThread().name
      println("$it printed on thread $threadName")
    }
  }
  //Thread.sleep(1000)
}
**/


//=============================================================
//This snippet sometimes print the message, sometimes does not
//=============================================================
/**
fun main() {
  GlobalScope.launch {

    println("This may or may not get printed")
  }


}
 **/


//=============================================================
//This snippet creates a coroutine and schedules a job lazily so
//that it can be started explicitly later
//==============================================================
/**
suspend fun main() {

  var jobRunCount: Int = 0
  val job = GlobalScope.launch(start = CoroutineStart.LAZY) {
    ++jobRunCount
    println("Coroutine> jobRunCount -> $jobRunCount")
    println("Coroutine> Job is active -> ${this.isActive}")

    delay(3000L)
    if (jobRunCount != 0) this.cancel()

  }

  println("Main> Job scheduled")
  println("Main> Job is active -> ${job.isActive}")
  println("Main> Job children count -> ${job.children.count()}")
  delay(3000L) // Observe, that LAZY works and the job is not started until we start it explicitly
  println("Main> Starting a previously scheduled work")
  job.start()
  println("Main> Job is active -> ${job.isActive}")
  println("Main> Job is running...")
  job.join()
  println("Main> Job finished")
  delay(3000L)
**/

//============================================================
//This snippet shows two jobs. One to which we save reference
//and one, which waits for the former, and only then completes
//=============================================================
/**
fun main() {

  with(GlobalScope) {
    val parentJob = launch {
      delay(200)
      println("I'm the parent")
      delay(200)
      var parentVariable: Int = 0
    }

    launch(context = parentJob) {
      delay(200)
      println("I'm a child")
      delay(200)
    }
    println("Parent job has children -> ${parentJob.children.count()}")
  }

  Thread.sleep(1000)
}
        **/

fun main() {

  


}

