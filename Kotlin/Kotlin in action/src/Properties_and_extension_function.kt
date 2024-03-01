
fun main() {
    val UsrCommitCount: Int = 201

    val u1: Usr = Usr(commitCount = UsrCommitCount, nickname = "Jon Doe", isSubscribed = true)

    println("According to isVipClassMethod(), Usr ${u1.nickname} is VIP: ${u1.isVipClassMethod()}")

    println("According to isVipExtensionFunction() (decideUsrVipA()), " +
            "Usr ${u1.nickname} is VIP: ${u1.isVipExtensionFunction { decideUsrVipA(it) }}")

    println("According to isVipExtensionFunction() (decideUsrVipB()), " +
            "Usr ${u1.nickname} is VIP: ${u1.isVipExtensionFunction { decideUsrVipB(it) }}")

    println("According to decision lambda, Usr ${u1.nickname} is VIP: ${u1.isVipExtensionFunction {
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

class Usr(var commitCount: Int = 0, val nickname: String, val isSubscribed: Boolean) {
    fun isVipClassMethod(): Boolean = this.commitCount > 0 && this.isSubscribed
}

fun Usr.isVipExtensionFunction(decisionFunction: (Usr) -> Boolean): Boolean {
    return decisionFunction(this)
}

fun decideUsrVipA(input: Usr): Boolean {
    return (input.commitCount > 100 && input.isSubscribed)
}

fun decideUsrVipB(input: Usr): Boolean {
    return (input.commitCount > 200 && input.isSubscribed)
}


