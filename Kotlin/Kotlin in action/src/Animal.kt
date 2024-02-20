//Actions and properties most animals posses

/**
open class Animal(var height: Float = 0.0f, var weight: Float = 0.0f, var limbs: Short = 4) {

    open fun eat() {
        println("Animal is eating")
    }

    open fun sleep() {
        println("Animal is sleeping")
    }
}
 **/

abstract class Animal {
    abstract var height: Float
    abstract var weight: Float

    abstract fun eat()
    abstract fun sleep()

}





