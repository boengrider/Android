fun main() {
    val number = 10
    
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")
    
    println(actionIncrement(number)) // Use a function type 'directly'
    println(actionSquare(number))    // Use a function type 'directly'
    println(actionDecrement(number)) // Use a function type 'directly'
    println(applyAction(number,actionIncrement)) // Pass a function type as an argument to applyAction
    
    /** 
     * 11
     * 100
     * 9
     * 11
    **/
   
    //val copyActionIncrement = actionIncrement
    //println(copyActionIncrement(20))
    //println(copyActionIncrement.invoke(50))
}


//decideAction returns a funtion type
fun decideAction(action: String): (Int) -> Int {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { input: Int -> input } // Do nothing
      
}

//applyAction takes function type as it's second parameter
fun applyAction(input: Int, action: (Int) -> Int): Int {
    
    return action(input)
}
