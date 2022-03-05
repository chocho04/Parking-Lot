package parking
import kotlin.system.exitProcess

class Parking {
    companion object {
        var lot = mutableMapOf<Int,MutableList<String>>()
    }
}
class Control {
    companion object {
        private var occupied = 0
        private var created = false


        fun park(userInput: String = readln()) {
            val input = userInput.split(" ")
            if (input[0] == "exit") { exitProcess(0) }
            if (input[0] == "create") {
                created = true
                occupied = 0
                Parking.lot.clear()
                println("Created a parking lot with ${input[1]} spots.")
                for (i in 1..input[1].toInt()) {
                    Parking.lot[i] = mutableListOf("", "")
                }
            }
            if (created) {
                when (input[0]) {
                    "park" -> {
                        for (i in Parking.lot) {
                            if (i.value[0].isEmpty()) {
                                Parking.lot[i.key] = mutableListOf(input[1], input[2])
                                occupied++
                                println("${input[2]} car parked in spot ${i.key}.")
                                break
                            }
                            if (occupied == Parking.lot.size) {
                                println("Sorry, the parking lot is full.")
                                break
                            }
                        }
                    }
                    "leave" -> {
                        val spot = userInput.split(" ").last().toInt()
                        if (Parking.lot[spot]?.get(0)?.isEmpty() == true) {
                            println("There is no car in spot $spot.")
                        } else {
                            Parking.lot[spot] = mutableListOf("", "")
                            occupied--
                            println("Spot $spot is free.")
                        }
                    }
                    "status" -> {
                        if (occupied == 0) { println("Parking lot is empty.") }
                        else {
                            for (car in Parking.lot) {
                                if (car.value[0].isNotEmpty()) {
                                    println("${car.key} ${car.value[0]} ${car.value[1]}") }
                            }
                        }
                    }
                    "reg_by_color" -> {
                        val rbc = mutableListOf<String>()
                        for (i in Parking.lot) {
                            if ( i.value[1].lowercase() == input[1].lowercase()) {
                                rbc.add(i.value[0])
                            }
                        }
                        if (rbc.isEmpty()) { println("No cars with color ${input[1]} were found.") }
                        else { println(rbc.joinToString ( ", " )) }
                    }
                    "spot_by_color" -> {
                        val sbc = mutableListOf<String>()
                        for (i in Parking.lot) {
                            if ( i.value[1].lowercase() == input[1].lowercase()) {
                                sbc.add(i.key.toString())
                            }
                        }
                        if (sbc.isEmpty()) { println("No cars with color ${input[1]} were found.") }
                        else { println(sbc.joinToString ( ", " )) }
                    }
                    "spot_by_reg" -> {
                        val sbr = mutableListOf<String>()
                        for (i in Parking.lot) {
                            if ( i.value[0].lowercase() == input[1].lowercase()) {
                                sbr.add(i.key.toString())
                            }
                        }
                        if (sbr.isEmpty()) { println("No cars with registration number ${input[1]} were found.") }
                        else { println(sbr.joinToString ( ", " )) }
                    }
                }
            } else { println("Sorry, a parking lot has not been created.") }
        }
    }
}
fun main() {
    while (true) {
        Control.park()
    }
}