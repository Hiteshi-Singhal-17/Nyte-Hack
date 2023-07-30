/*
Narration Modifier is a function type that takes a string argument and returns a string.
The function literal don't need the return keyword. By default, last line of lambda is returned.
It's both convenience and necessity of syntax, because it could be ambiguous to the compiler which
function the return is from: the lambda expression itself or the function that called it.
"It" is used when lambda accepts one argument only.
 */
val narrationModifier: (String) -> String = {
    val numExclamationPoints = 3
    it.uppercase() + "!".repeat(numExclamationPoints)
}

/**
 * Hosting narrator and its mood
 */
fun narrate( message: String) {
    println(narrationModifier(message))
}