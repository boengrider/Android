fun main() {
    val fu = FacebookUser(1203)

    fu.accountId

    Thread.sleep(4000)

    println(fu.accountId)
}

interface User {
    val nickname: String
}

//Primary constructor only. Notice override directly in the constructor
class PrivateUser(override val nickname: String) : User

class FacebookUser(val accountId: Int) : User {
    override val nickname = getFacebookUser(accountId)
}

fun FacebookUser.getFacebookUser(accountId: Int): String {
    println("Property initializer called")
    return "Jon Doe"
}
