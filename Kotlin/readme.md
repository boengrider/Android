# This folder contains simple kotlin snippets while learning the language

---
+ ## Function types

Following function takes a **String** parameter and returns a **function-type**      
Returned **function-type** takes an **Int** parameter and returns an **Int**
```kotlin
fun decideAction(action: String): (Int) -> Int {
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return { input: Int -> input } // Do nothing
}
```

Following function has two parameters   
1st parameter is of type **Int**
The 2nd parameter is a **function-type** which itself has one **Int** parameter and returns **Int**
```kotlin
fun applyAction(input: Int, action: (Int) -> Int): Int {   
    return action(input)
}
```
Above function definition can be simplified a bit like this   
```kotlin
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
```

```kotlin
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

