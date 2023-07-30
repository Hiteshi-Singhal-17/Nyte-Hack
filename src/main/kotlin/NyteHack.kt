fun main() {
    // ::makeYellow -> Example of function reference.
    narrate("A hero enters the town of Kronstadt. What is their name?", ::makeYellow)
    val heroName = readlnOrNull() ?: ""
    require(heroName.isNotEmpty()) {
        "The hero must have a name."
    }

    changeNarratorMood()
    narrate("$heroName, ${createTitle(heroName)} heads to the town square")
}

private fun createTitle(name: String): String {
    return when {
        name.all { it.isDigit() } -> "The Identifiable"
        name.none { it.isLetter() } -> "The Witness Protection Member"
        name.count { it.lowercase() in "aeiou" } > 4 -> "The Master of Vowels"
        name.all {it.isUpperCase()} -> "The Bold"
        name.length > 10 -> "The Verbose"
        name.reversed().equals(name,true)-> "Bringer of Palindrome"
        else -> "The Renowned Hero"
    }
}

private fun makeYellow(message: String) = "\u001b[33;1m$message\u001b[0m"