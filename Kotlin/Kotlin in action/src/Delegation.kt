import kotlin.reflect.KProperty

fun main() {

    val cset = CountingSet<Int>()
    cset.add(1)
    cset.innerSet.add(2)
    println(cset.objectsAdded)
    println(cset.size)

    val wt = WriteTracker<Int>()
    wt.add(10)
    println(wt.isEmpty())
    println(wt.innerSet.isEmpty())




}


class Delegate(private var actions: List<((String) -> Unit)>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        actions.forEach {
            it(value)
        }
    }
}

class WriteTracker<T>(val innerSet: MutableCollection<T> = ArrayList<T>()) : MutableCollection<T> by innerSet {
    var numberOfWrites: Int = 0

    //override fun isEmpty(): Boolean = numberOfWrites == 0

}

class CountingSet<T>(val innerSet: MutableCollection<T> = HashSet<T>()) : MutableCollection<T> by innerSet {

    var objectsAdded = 0

    override fun add(element: T): Boolean {
        objectsAdded++
        return innerSet.add(element)
    }

    override fun addAll(c: Collection<T>): Boolean {
        objectsAdded += c.size
        return innerSet.addAll(c)
    }
}

object Payroll {
    val allEmployees = arrayListOf<Person>(
        Person("Jon Doe", 32, 11, 20),
        Person("Jane Doe", 29, 11, 3)
    )

    fun calculateSalary() {
        for (person in allEmployees) {
            if (person.level > 10 && person.years >= 5) {
                println("Calculated salary coefficient for ${person.name} is ${person.level * (person.years / 5)}")
            } else {
                println("Calculated salary coefficient for ${person.name} is ${person.level * 1}")
            }
        }
    }
}

class Person(val name: String, var age: Int, var level: Int, var years: Int)



