import kotlin.concurrent.thread

//This blocks the main thread for 5 seconds
/**
fun main() {
  val user = getUserStandard("101")
  val admin = Admin("0","root",5)

  println(user)
  println(admin)
}

fun getUserStandard(userId: String): User {
  Thread.sleep(5000)

  return User(userId, "Filip")
}
        **/


//This creates a new thread and notifies main thread via callback
fun main() {

  getUserNotifyViaCallback("101") {
    println("callback> Callback executed on thread -> ${Thread.currentThread().name}. User is $it")
  }

  println("main> getUserNotifyViaCallback() function has returned")
  println("main> Thread name: ${Thread.currentThread().name}")
}



fun getUserNotifyViaCallback(user: String, onUserReady: (User) -> Unit) {

  thread {

    println("getUserNotifyViaCallback> New thread created -> ${Thread.currentThread().name}")
    Thread.sleep(2000) // Simulate network delay

    val user = User(user, "Jon Doe") // Create a new user, as if returned by network

    onUserReady(user) // Pass newly created user to the callback function type


  }

  println("Putting this thread -> ${Thread.currentThread().name} to sleep for 5 seconds")
  Thread.sleep(5000)

  thread {

    println("getUserNotifyViaCallback> New thread created -> ${Thread.currentThread().name}")
    Thread.sleep(2000)

    val user = User(user, "Jane Doe")

    onUserReady(user)
  }

}
