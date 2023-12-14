interface Animal {
    val animal: String // Abstract. Override in the class
    val breed: String // Abstract. Override in the class
    
    fun petAnimal() {
        println("Petting a ${breed} ${animal}")
    }
    
    fun feedAnimal() // Abstract. Override in the class
}

class Dog(override val breed: String) : Animal {
   override val animal: String = "Dog"
    
   override fun feedAnimal() {
       println("Feeding the dog")
   }
   
   override fun petAnimal() = println("This petting method is overriden in Dog")
}

class Cat(override val breed: String ) : Animal {
    override val animal: String = "Cat"
    
    override fun feedAnimal() {
        println("Feeding the cat")
    }
}

fun main() {
    
    val c = Cat("Scottish fold")
    val d = Dog("Husky")
    
    c.feedAnimal()
    d.feedAnimal()
    c.petAnimal()
    d.petAnimal()
    println("Cat is an animal -> ${c is Animal}")
    println("Dog is an animal -> ${d is Animal}")

    /**
    Feeding the cat
    Feeding the dog
    Petting a Scottish fold Cat
    This petting method is overriden in Dog
    Cat is an animal -> true
    Dog is an animal -> true
    **/
}



