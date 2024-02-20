
fun main() {
    val gsp = GermanShorthairedPointer("Joey")
    val jrt = JackRussellTerrier("Milo")
    gsp.bark()

    val cha = Chihuahua("Molly")
    cha.bark()

    gsp.fetch()

    val cat = Cat(height = 2.3f)
    println(cat.height)
    println(cat.weight)

    println(gsp.name)


    val p1 = Person()
    val p2 = Person(10)
    val e1 = Employee()
    val e2 = Employee(20)

    println("${p1.age}\n${p2.age}\n${e1.person.age}\n${e2.person.age}")

    val pa1 = PersonA()

    println(pa1.age)



}

data class Person(val age: Int = 99) {

}

class Employee(age: Int = 99) {
    val person: Person = Person(age)
}

open class PersonA(val age: Int = 99) {}
class EmployeeA : PersonA() {}
