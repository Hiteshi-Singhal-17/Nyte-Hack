import kotlin.random.Random
import kotlin.random.nextInt

/*
Narration Modifier is a function type that takes a string argument and returns a string.
The function literal don't need the return keyword. By default, last line of lambda is returned.
It's both convenience and necessity of syntax, because it could be ambiguous to the compiler which
function the return is from: the lambda expression itself or the function that called it.
"It" is used when lambda accepts one argument only.
 */
var narrationModifier: (String) -> String = { it }

/**
 * Hosting narrator and its mood
 */
fun narrate(
    message: String,
    modifier: (String) -> String = { narrationModifier(it) }
) {
    println(narrationModifier(message))
}

fun changeNarratorMood() {
    val mood: String
    val modifier: (String) -> String

    when (Random.nextInt(1..7)) {
        1 -> {
            mood = "loud"
            modifier = { message ->
                val numExclamationPoints = 3
                message.uppercase() + "!".repeat(numExclamationPoints)
            }
        }

        2 -> {
            mood = "tired"
            modifier = { message ->
                message.lowercase().replace(" ", "...")

            }
        }

        3 -> {
            mood = "unsure"
            modifier = { message ->
                "$message?"
            }
        }

        4 -> {
            var narrationsGiven = 0
            mood = "like something an itemized bill"
            modifier = { message ->
                // Example of closure.
                narrationsGiven++
                "$message.\n(I have narrated $narrationsGiven things)"
            }
        }

        5 -> {
            mood = "lazy"
            modifier = { message ->
                message.take(message.length / 2)
            }
        }

        6 -> {
            mood = "leet"
            modifier = { message ->
                val leetMapping = mapOf(
                    'a' to '4',
                    'e' to '3',
                    'l' to '1',
                    'o' to '0',
                    't' to '7'
                    // Add more mappings as needed
                )
                message.lowercase().map { leetMapping[it] ?: it }.joinToString("")
            }
        }

        7 -> {
            mood = "poetic"
            modifier = { message ->
                val words = message.split(" ")
                val builder = StringBuilder()
                for (word in words) {
                    builder.append(word)
                    if (Random.nextBoolean()) { // Randomly decide whether to add a newline
                        builder.append("\n")
                    } else {
                        builder.append(" ")
                    }
                }
                builder.toString().trimEnd() // remove trailing whitespace
            }
        }

        else -> {
            mood = "professional"
            modifier = { message ->
                "$message."
            }
        }
    }
    narrationModifier = modifier
    narrate("The narrator begins to feel $mood")
}