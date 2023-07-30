import kotlin.random.Random
import kotlin.random.nextInt

/*
Narration Modifier is a function type that takes a string argument and returns a string.
The function literal don't need the return keyword. By default, last line of lambda is returned.
It's both convenience and necessity of syntax, because it could be ambiguous to the compiler which
function the return is from: the lambda expression itself or the function that called it.
"It" is used when lambda accepts one argument only.
 */
var narrationModifier: (String) -> String = {it}

/**
 * Hosting narrator and its mood
 */
fun narrate(
    message: String,
    modifier: (String) -> String = {narrationModifier(it)}
) {
    println(narrationModifier(message))
}

fun changeNarratorMood() {
    val mood : String
    val modifier: (String) -> String

    when (Random.nextInt(1..5)) {
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

        4-> {
            var narrationsGiven = 0
            mood = "like something an itemized bill"
            modifier = { message ->
                // Example of closure.
                narrationsGiven++
                "$message.\n(I have narrated $narrationsGiven things)"
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