import java.net.URL

fun main() {
    val fu = FacebookUser(120)


    //On first access, call initializer function
    fu.accountId


    //On subsequent access, return stored value
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
    val result: String = URL("https://zive.aktuality.sk/").readText()[10].toString()
    println(result)

    return result
}
