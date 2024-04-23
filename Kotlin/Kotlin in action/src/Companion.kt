fun main() {
    val e1 = Employer.newEmployerFromEmail("jon.doe@email.com")
    println(e1.firstName)
    println(e1.lastName)

    val e2 = Employer.newEmployerFromJSON("JSONPayload")
    println(e2.firstName)
    println(e2.lastName)
}

class Employer(val firstName: String, val lastName: String) {

    companion object {
        fun newEmployerFromEmail(email: String): Employer {
            val emailComponent = email.substringBefore('@')

            return Employer(firstName = emailComponent.split('.')[0],
                            lastName = emailComponent.split('.')[1])
        }


    }
}

//Extension function of the Employer companion object
fun Employer.Companion.newEmployerFromJSON(JSONPayload: String): Employer {
    return Employer(firstName = "FirstNameFromJSON",
        lastName = "LastNameFromJSON")
}