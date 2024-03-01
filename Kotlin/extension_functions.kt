
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







data class Person(val age: Int = 99) {

}

class Employee(age: Int = 99) {
    val person: Person = Person(age)
}

open class PersonA(val age: Int = 99) {}
class EmployeeA : PersonA() {}
