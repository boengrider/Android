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
    val actionFunctionTypeOrNull: ((Int) -> Int)? = decideActionFunctionTypeOrNull("**")
    
   
    println(actionFunctionTypeOrNull?.invoke(10))
    println(actionFunctionTypeOrNull.toString())
    
    
    
    
    
    
    
    
    
    // Even though there is '*=' action (square), and
    // function decideActionFunctionTypeResultOrNull will
    // indeed return a function-type that function-type itself can return null
    // e.g. if parameter passed to the function-type itself is zero
    val actionFunctionTypeResultOrNull: (Int) -> Int? = decideActionFunctionTypeResultOrNull("*=")
    val result: Int? = actionFunctionTypeResultOrNull(0)
    println((result == null)) // true
  
    
   
}


/***********************************
 * Return a function-type
 * Even if specified action doesn't
 * exist, return function type
 * which simply return it's parameter
 * unmodified
 * *********************************/
fun decideAction(action: String): (Int) -> Int {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { input: Int -> input } // Return the value unmodified
      
}

/***********************************
 * Return a function-type OR null
 * ((Int) -> Int)? means, either return 
 * a function-type with this signature
 * or in the case action doesn't exist
 * return null
 ***********************************/

fun decideActionFunctionTypeOrNull(action: String): ((Int) -> Int)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // No such action exists
  
}

/***********************************
 * Return a function-type 
 * Which returns an Int, and if the
 * parameter is zero, it returns a null
 ***********************************/
 
fun decideActionFunctionTypeResultOrNull(action: String): (Int) -> Int? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> if(input != 0) { input * input } else { null } } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement

  // Here we can decide, either return a unmodified non-null value or null
  return { input: Int -> input } // No such action exists
 
}

/***********************************
 * Return a function-type or a null 
 * Function-type returns an Int, and if the
 * parameter is zero, it returns a null
 ***********************************/
fun decideActionFunctionTypeOrNullResultOrNull(action: String): ((Int) -> Int?)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> if(input != 0) { input * input } else { null } } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // No such action exists
}


//applyAction takes function type as it's second parameter. Simpliefied
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
