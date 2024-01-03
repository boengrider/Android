fun main() {
    val number = 10
    
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")
    
    // Increment the number
    println(actionIncrement(number)) 
    println(actionSquare.invoke(number))
    println(actionDecrement(number)) // Use a function type 'directly'
    
    println(applyAction(number,actionIncrement))

    println(applyAction(number,actionSquare))

    println(applyAction(number,actionDecrement))
    
    println(actionSquare.invoke(number)) // 100
    /** 
     * 11
     * 100
     * 9
     * 11
    **/
   
    //There is no '**' action. null should be returned from the decideAction function instead of a function-type
    val actionIncrementNullable: ((Int) -> Int)? = decideActionNullable("**") 
    
    // True
    println(actionIncrementNullable == null) 
    
    // Type mismatch: inferred type is ((Int) -> Int)? but (Int) -> Int was expected
    //println(applyAction(number,actionIncrementNullable)) 
}


//decideAction returns a funtion type
fun decideAction(action: String): (Int) -> Int {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { input: Int -> input } // Do nothing
      
}

//Return a function-type OR null
// ((Int) -> Int)?   Either return a function-type or a null
//                     vs
// (Int) -> Int?     Return a function-type which itself returns an Int or a null
fun decideActionNullable(action: String): ((Int) -> Int)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // Do nothing
  
}

//applyAction takes function type as it's second parameter. Simpliefied
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
