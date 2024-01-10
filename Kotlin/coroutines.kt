import kotlinx.coroutines.*
import kotlin.time.Duration

class BoiledWater(tmp: Int) {
    var temperature: Int = tmp
    	get() = field
        set(value) {
            field = value
        }
}

suspend fun main() {
    val water = BoiledWater(24)
    GrindAndBoil(water)
    BoilWater(water) //Repeat boiling if necesary
    println("Water temperature is ${water.temperature}")
 
    
}

suspend fun GrindAndBoil(waterToBoil: BoiledWater) {
   
    val begin = System.nanoTime()
    
    coroutineScope {
        launch {
            //Grind the coffee grounds
            println("Grinding the coffee grounds...")
            delay(2000L) //Simulate longer running job
            waterToBoil.temperature = 70 // Simulate water cooling off because of grinding taking longer
        }
        
        launch {
            //Boil water in the kattle
            println("Boiling water in the kattle...")
            delay(2000L)
            waterToBoil.temperature = 100 // Raise the water temperature
           
        }
    }
    
    val end = System.nanoTime()
    
    println("Boiling and grinding done in ${(end - begin) / 1000000000} seconds")
   
   
}

suspend fun BoilWater(waterToBoil: BoiledWater) {
    if(waterToBoil.temperature < 100) {
        println("Difference in temperature is ${100 - waterToBoil.temperature}. Boiling is necessary")
        waterToBoil.temperature = 100
    }
}
