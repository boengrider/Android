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
