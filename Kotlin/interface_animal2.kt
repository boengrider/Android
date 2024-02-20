//Actions and properties most animals posses
open class Animal(height: Float = 0.0f, weight: Float = 0.0f, limbs: Short = 4) {
    
    var height: Float = height
    var weight: Float = weight
    var limbs: Short = limbs
    
    open fun eat() {
        println("Animal is eating")
    }
    
    open fun sleep() {
        println("Animal is sleeping")
    }
}

//Actions only dogs can perform
interface DogStuff {
    fun bark() {
        println("A dog is barking")
    }  
}

//Actions only cats can perform
interface CatStuff {
    fun meow() {
        println("A cat is meowing")
    }
}


//Each dog is an instance of animal and can perform
//dog specific actions
open class Dog : Animal(), DogStuff  {
    override fun bark() {
        super<DogStuff>.bark()
    }
}

//Each GPD is an instance of a dog, but can bark 
//a little bit differently
final class GermanShorthairedPointer : Dog() {
	override fun bark() {
        println("A German shorthaired pointer is barking")
    }
}

final class Chihuahua : Dog() {
    //No override of barking method. Thus 
    //poor Chihuahua barks like a generic dog (whatever that might be)
}


fun main() {
    
    val gsp = GermanShorthairedPointer()
    gsp.bark()
    
    val cha = Chihuahua()
    cha.bark()
    
}
