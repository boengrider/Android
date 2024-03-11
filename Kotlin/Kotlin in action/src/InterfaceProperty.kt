import org.omg.CORBA.Object
import java.net.URL

fun main() {

    val map = hashSetOf(Client("Jane Doe", 90301), Client("Jon Doe", 90301))

    map.forEach { println(it.name) }

    val cla = Client(name = "Jon Doe", postalCode = 90301)
    val clb = Client(name = "Jon Doe", postalCode = 90301)

    println("cla -> ${cla.toString()}")
    println("clb -> ${clb.toString()}")

    println("cla hash -> ${cla.hashCode()}")
    println("clb hash -> ${clb.hashCode()}")

    //Compare references
    println("cla === clb -> ${cla === clb}")
    //Compare object equality. This method is overridden and returns true if hash value of both object IS DIFFERENT
    println("cla == clb -> ${cla == clb}")






}

interface User {
    val nickname: String
}

//Primary constructor only. Notice override directly in the constructor
class PrivateUser(override val nickname: String) : User

//Primary constructor only
//Derives from User
//Implements nickname property
class FacebookUser(val accountId: Int) : User {
    //Initializer function. Only called once. Subsequent calls return stored value in the field
    override val nickname = getFacebookUser(accountId)
}

//Primary constructor only
//Derives from User
//Implements nickname property
class SubscribingUser(private val email: String) : User {
    //nickname property is computed from the email property on every access
    //no value is stored
    //no backing field
    override val nickname: String
        get() = email.substringBefore('@')
}

fun FacebookUser.getFacebookUser(accountId: Int): String {
    println("Property initializer called")

    val result: String = URL("https://zive.aktuality.sk/").readText()[10].toString()

    return result
}

interface Admin {
    val email: String
    val nickname: String
        get() = email.substringBefore('@')
}


class MainAdmin(override val email: String) : Admin

//Each class derives from object ?
class Client(val name: String, val postalCode: Int)  {
    override fun toString() = "Client(name=$name, postalCode=$postalCode)"

    //Override equals method
    override fun equals(other: Any?): Boolean = this.hashCode() == other.hashCode()

    //Override hashCode method
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}
