open class Person(age: Int, val name: String) {
    var age: Int = age
    	get() = field
        set(value) { field = value; EligibleForStatePension(age)}
        
    val isPensioner: Boolean // No backing field. Boolean value is computed making comparison, no need to store data
        get() = this.age >= 60 
}

class Employee(age: Int, name: String) : Person(age, name)

fun main() {
    
    val e = Employee(58,"Jon Doe")
    println("Employee ${e.name}, age ${e.age} is pensioner -> ${e.isPensioner}")
    e.age++  
    e.age++
    println("Employee ${e.name}, age ${e.age} is pensioner -> ${e.isPensioner}")
}

fun EligibleForStatePension(age: Int) {
    
    if (age >= 60) { 
        println("Eligible")
    } else {
        println("Not eligible")
    }
}