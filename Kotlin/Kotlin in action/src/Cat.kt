//Actions only cats can perform
interface CatStuff {
    fun meow() {
        println("A cat is meowing")
    }
}

open class Cat(override var height: Float = 0.0f, override var weight: Float = 0.0f) : Animal(), CatStuff  {


    override fun eat() {
        println("A cat is eating [overridden method of an Animal()]")
    }

    override fun sleep() {
        println("A cat is sleeping [overridden method of an Animal()]")
    }

}