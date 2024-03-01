
fun main() {
    val userCommitCount: Int = 201

    val u1: User = User(commitCount = userCommitCount, nickname = "Jon Doe", isSubscribed = true)

    println("According to isVipClassMethod(), user ${u1.nickname} is VIP: ${u1.isVipClassMethod()}")

    println("According to isVipExtensionFunction() (decideUserVipA()), " +
            "user ${u1.nickname} is VIP: ${u1.isVipExtensionFunction { decideUserVipA(it) }}")

    println("According to isVipExtensionFunction() (decideUserVipB()), " +
            "user ${u1.nickname} is VIP: ${u1.isVipExtensionFunction { decideUserVipB(it) }}")

    println("According to decision lambda, user ${u1.nickname} is VIP: ${u1.isVipExtensionFunction { 
        it.nickname.startsWith("J",true) && it.commitCount > 100 && it.commitCount < 200
    }}")

    //Declare subscriber
    val el = EventListener()

    el.action(10)

}

interface Subscriber {
    val action: (Int) -> Unit
}


class EventListener() : Subscriber {
    override val action: (Int) -> Unit = { println("You have accessed event listener field $it")}
        get() = field

}
class Monitor(valueToMonitor: Int, val action: (Int) -> Unit) {
    var valueToMonitor: Int = valueToMonitor
        get() = field
        set(value) {
            field = value
            action(value)

        }
}

class User(var commitCount: Int = 0, val nickname: String, val isSubscribed: Boolean) {
    fun isVipClassMethod(): Boolean = this.commitCount > 0 && this.isSubscribed
}

fun User.isVipExtensionFunction(decisionFunction: (User) -> Boolean): Boolean {
    return decisionFunction(this)
}

fun decideUserVipA(input: User): Boolean {
    return (input.commitCount > 100 && input.isSubscribed)
}

fun decideUserVipB(input: User): Boolean {
    return (input.commitCount > 200 && input.isSubscribed)
}


