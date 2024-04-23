import org.omg.CORBA.Object
import java.net.URL

fun main() {

    val e1 = Employer.newEmployerFromEmail("jon.doe@email.com")
    println(e1.firstName)
    println(e1.lastName)

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

    //Override equals method (operator)
    override fun equals(other: Any?): Boolean = this.hashCode() == other.hashCode()

    //Override hashCode method
    override fun hashCode(): Int = name.hashCode() * 31 + postalCode
}
