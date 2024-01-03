# This folder contains simple kotlin snippets while learning the language

---
+ ## Function types
[source file](function_types.kt)

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

Use the function-types 'directly', refering to variables storing them
```kotlin
fun main() {
    val number = 10

    // Store the function-type in the variables
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")

    // Refer to the stored function-types 'directly'
    println(actionIncrement(number)) // 11
    println(actionSquare(number))    // 100
    println(actionDecrement(number)) // 9

}
```

Call the function-type's invoke method explicitly
```kotlin
fun main() {
    val number = 10

    // Store the function-type in the variables
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")

    // Refer to the stored function-types 'directly'
    println(actionIncrement.invoke(number)) // 11
    println(actionSquare.invoke(number))    // 100
    println(actionDecrement.invoke(number)) // 9

}
```


Use the function-types 'indirectly', passing a function-type argument to another function   
Here the applyAction function takes a function-type as it's 2nd parameter
```kotlin
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
```
```kotlin
fun main() {
    val number = 10

    // Store the function-type in the variables
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")

    println(applyAction(number,actionIncrement)) // 11
    println(applyAction(number,actionSquare))    // 100
    println(applyAction(number,actionDecrement)) // 9


}
```

### Nullables   

Define a function returning a function-type or null

```kotlin
fun decideActionNullable(action: String): ((Int) -> Int)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // No such action exists
  
}
```

#                             versus

//Return a function-type OR null
// ((Int) -> Int)?   Either return a function-type or a null
//                     vs
// (Int) -> Int?     Return a function-type which itself returns an Int or a null
    

