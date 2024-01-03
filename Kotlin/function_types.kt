fun main() {
    val number = 10
    
    // Store the function-types
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")
    
    // Use a function type 'directly'
    println(actionIncrement(number)) // 11
    println(actionSquare(number))    // 100
    println(actionDecrement(number)) // 9
    
    // Pass a function-type as an argument
    println(applyAction(number,actionIncrement)) // 11
    println(applyAction(number,actionSquare))    // 100
    println(applyAction(number,actionDecrement)) // 9
    
    // Call function-type's invoke method explicitly
    println(actionIncrement.invoke(number)) // 11
    println(actionSquare.invoke(number))    // 100
    println(actionDecrement.invoke(number)) // 9
    
    // Nullables
    
    // There is no such action as '**', therefore decideActionNullable() will return null
    val actionSquareNullable: ((Int) -> Int)? = decideActionFunctionTypeNullable("**")
    
    if (actionSquareNullable == null) println("No such action exists") //No such action exists
    
    
    // Even though there is '*=' action (square), that function-type itself can return null
    // e.g. if parameter passed to the function-type itself is zero
    val actionSquareActionResultNullable: (Int) -> Int? = decideActionFunctionTypeResultNullable("*=")
    val result: Int? = actionSquareActionResultNullable(0)
    println((result == null)) // true
  
    
   
}


//decideAction returns a funtion type
fun decideAction(action: String): (Int) -> Int {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { input: Int -> input } // Return the value unmodified
      
}

//Return a function-type OR null
// ((Int) -> Int)?   Either return a function-type or a null
fun decideActionFunctionTypeNullable(action: String): ((Int) -> Int)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // Do nothing
  
}


fun decideActionFunctionTypeResultNullable(action: String): (Int) -> Int? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> if(input != 0) { input * input } else { null } } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { null } // No such action exists
  
}


//applyAction takes function type as it's second parameter. Simpliefied
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
