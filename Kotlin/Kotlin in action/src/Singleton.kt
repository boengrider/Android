fun main() {
    val o1 = object  {
        var v: Int = 1
    }

    val o2 = object {
        var v: Int = 2
    }

    println(o1.v)
    println(o2.v)

    val u1 = Utility
    u1.init(1)

    val u2 = Utility
    u2.init(2)

    println(u1.number)
    println(u2.number)

    println(u1)
    println(u2)
}

object Utility {
    var number: Int = 0

    fun init(number: Int): Unit {
        this.number = number
    }
    fun translateToUpperCase(input: String): String = input.uppercase()
}


