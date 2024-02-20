//Actions only dogs can perform
interface DogStuff {
    fun bark() {
        println("A dog is barking")
    }
}


//Each dog is an instance of animal and can perform
//dog specific actions
//Dog class inherits from the abstract class Animal and DogStuff interface
open class Dog(val name: String = "") : Animal(), DogStuff  {

    override var height: Float = 0.0f
    override var weight: Float = 0.0f

    override fun eat() {
        println("A dog is eating [overridden method of an Animal()]")
    }

    override fun sleep() {
        println("A dog is sleeping [overridden method of an Animal()]")
    }

}

//Each GPD is an instance of a dog, but can bark
//a little bit differently
//Also no more inheriting from the GermanShorthairedPointer
//Mark bark() overridden in this class as final
class GermanShorthairedPointer(name: String) : Dog(name) {

    val breed: String = "German shorthaired pointer"

    override fun bark() {
        println("A German shorthaired pointer is barking [overridden method of a Dog]")
    }
}

class Chihuahua(name: String) : Dog(name) {

    val breed: String = "Chihuahua"

    override fun bark() {
        println("A Chihuahua is barking [overridden method of a Dog]")
    }
}

class JackRussellTerrier(name: String) : Dog(name) {

    val breed: String = "Jack russell terrier"

    override fun bark() {
        println("A Jack russell terrier is barking [overridden method of a Dog]")
    }
}

//Extension function of a type GermanShorthairedPointer
//No all dogs fetch, so we will not define this in the Dog class
fun GermanShorthairedPointer.fetch() {
    println("$breed is fetching [extension function of a Dog]")
}