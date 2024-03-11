import kotlin.reflect.KProperty

fun main() {

   val watcherA: (String) -> Unit = {
       println("$it from watcher A")
   }

    val watcherB: (String) -> Unit = {
        println("$it from watcher B")
    }

    var p: String by Delegate(listOf(watcherA,watcherB))

    p = "Hello World"




}


class Delegate(private var actions: List<((String) -> Unit)>) {
    operator fun getValue(thisRef: Any?, property: KProperty<*>): String {
        return "$thisRef, thank you for delegating '${property.name}' to me!"
    }

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: String) {
        actions.forEach {
            it(value)
        }
    }
}



