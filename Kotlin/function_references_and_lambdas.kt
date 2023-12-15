fun main() {
   
  
  val inputList = listOf(1,2,3,4,5,6,7,8,9)
  
  // Squared using function reference
  println(inputList.map(::square)) //[1, 4, 3, 16, 5, 36, 7, 64, 9]
  
  // Pointer to function reference
  val functionPointer = ::square
  println(inputList.map(functionPointer)) //[1, 4, 3, 16, 5, 36, 7, 64, 9]
  
  
  // Named lambda 'square'
  val squareLambda = { item: Int -> if(item % 2 == 0) item * item else item }
  
  // Named lambda 'cube'
  val cube = { item: Int -> if(item % 2 == 0) square(item) * item else item }
 
  // Squared using named labmda
  println(inputList.map(squareLambda)) //[1, 4, 3, 16, 5, 36, 7, 64, 9]
  
  // Cubed using named lambda which itself calls named function
  println(inputList.map(cube)) //[1, 8, 3, 64, 5, 216, 7, 512, 9]
  
  
  // Lambda at callsite
  println(inputList.map { it * it}) //[1, 4, 9, 16, 25, 36, 49, 64, 81]
  
    
  
      
}


// Square if 'item' is even
fun square(item: Int): Int = if(item % 2 == 0) item * item else item




