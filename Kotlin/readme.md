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
    // Store the function-type in the variables
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")

    // Refer to the stored function-types 'directly'
    println(actionIncrement.invoke(10)) // 11
    println(actionSquare.invoke(10))    // 100
    println(actionDecrement.invoke(10)) // 9
```


Use the function-types 'indirectly', passing a function-type argument to another function   
Here the applyAction function takes a function-type as it's 2nd parameter
```kotlin
fun applyAction(input: Int, action: (Int) -> Int): Int = action(input)
```
```kotlin
    // Store the function-type in the variables
    val actionIncrement = decideAction("++")
    val actionSquare = decideAction("*=")
    val actionDecrement = decideAction("--")

    println(applyAction(10,actionIncrement)) // 11
    println(applyAction(10,actionSquare))    // 100
    println(applyAction(10,actionDecrement)) // 9

```

---
### Nullables  
---

#### Three use cases
+ Use case ${\color{green}A}$	 ((Int) -> Int)?
+ Use case ${\color{green}B}$  (Int) -> Int?
+ Use case ${\color{green}C}$  ((Int) -> Int?)?
<br><br> 

#### Use case ${\color{green}A}$
Define a function returning a function-type or null

```kotlin
fun decideActionFunctionTypeOrNull(action: String): ((Int) -> Int)? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> input * input } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement
  
  return null // No such action exists
  
}
```

```kotlin
    // There is no such action as '**', therefore decideActionFunctionTypeOrNull function will return null
    val actionFunctionTypeOrNull: ((Int) -> Int)? = decideActionFunctionTypeOrNull("**")

    // This won't compile with the following error message
    // Reference has a nullable type '((Int) -> Int)?', use explicit '?.invoke()' to make a function-like call instead
    // println(actionFunctionTypeOrNull(10))

     // Call invoke explicitly
     println(actionFunctionTypeOrNull?.invoke(10)) // null
     // Since actionFunctionTypeOrNull is nullable type, we
     // probably get text representation of it. Similar to
     println(actionFunctionTypeOrNull.toString())  // null
    
    
```
<br><br>

#### Use case ${\color{green}B}$
Define a function returning a function-type which itself returns an Int or null
```kotlin
fun decideActionFunctionTypeResultOrNull(action: String): (Int) -> Int? {
    
  if(action == "++") return { input: Int -> input + 1 } // Increment
  
  if(action == "*=") return { input: Int -> if(input != 0) { input * input } else { null } } // Square
  
  if(action == "--") return { input: Int -> input - 1 } // Decrement

  // Here we can decide, either return a unmodified non-null value or null
  return { input: Int -> input } // No such action exists
  
}
```

```kotlin
    // Even though there is '*=' action (square), and
    // function decideActionFunctionTypeResultOrNull will
    // indeed return a function-type that function-type itself can return null
    // e.g. if parameter passed to the function-type itself is zero
    val actionFunctionTypeResultOrNull: (Int) -> Int? = decideActionFunctionTypeResultOrNull("*=")
    val result: Int? = actionFunctionTypeResultOrNull(0)
    println((result == null)) // true
```
    

