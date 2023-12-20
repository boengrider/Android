import kotlin.math.*
// Named lambda
val opIncrement = { x: Int -> var tmp: Int = x; ++tmp}

fun main() {
    
    // Apply the operation
	var result: Int = transform(10) {
        var tmp: Int = it
        ++tmp
    }
    println(result) // 11
    
    // Apply the operation
    result = transform(11, opIncrement)
    println(result) // 12
    
    
}

fun transform(input: Int, operation: (Int) -> Int): Int {
      var tmp: Int = operation(input)
      
      return tmp
}
