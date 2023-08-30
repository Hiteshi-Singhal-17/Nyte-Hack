import java.io.File

private const val TAVERN_MASTER = "Taernyl"
private const val TAVERN_NAME = "$TAVERN_MASTER's Folly"

private val firstNames = setOf("Alex", "Mordoc", "Sophie", "Tariq")
private val lastNames = setOf("Ironfoot", "Fernsworth", "Baggins", "Downstrider")


private val menuData = File("src/main/data/tavern-menu-data.txt")
    // Read the text from file into a string
    .readText()
    // Returns a List<String>,splitting the string that appears between delimiter.
    .split("\n")




private val menuItems = List(menuData.size) { index ->
    val (_, name, _) = menuData[index].split(",")
    name
}

private val menuItemsPrices: Map<String, Double> = List(menuData.size) { index ->
    val (_, name, price) = menuData[index].split(",")
    name to price.toDouble()
}.toMap()

val menuItemTypes: Map<String, String> = List(menuData.size) { index ->
    val (type, name, _) = menuData[index].split(",")
    name to type
}.toMap()

fun visitTavern() {
    narrate("$heroName enters $TAVERN_NAME")
    narrate("There are several items for sale:")
    println()
    displayMenu(menuItemsPrices)
    println()
    val patronGold = mutableMapOf(
        TAVERN_MASTER to 86.00,
        heroName to 4.50
    )

    val patrons: MutableSet<String> = mutableSetOf()
    while (patrons.size < 5) {
        val patronName = "${firstNames.random()} ${lastNames.random()}"
        patrons += patronName
        // Every patron gets 6 gold coins.
        patronGold += patronName to 6.0
    }

    narrate("$heroName sees several patrons in the tavern:")
    narrate(patrons.joinToString())

    repeat(3) {
        placeOrder(patrons.random(), menuItems.random(), patronGold)
    }
    displayPatronBalances(patronGold)
}

fun displayMenu(itemsWithPrice: Map<String, Double>) {
    println("*** Welcome to Taernyl's Folly ***")
    itemsWithPrice.forEach {(dish, price) ->
        val itemPrice = "%.2f".format(price)
        println("${dish.padEnd(28,'.')}${itemPrice.padStart(5,'.')}")
    }
}

fun placeOrder(
    patronName: String,
    menuItemName: String,
    patronGold: MutableMap<String, Double>,
) {
    val itemPrice = menuItemsPrices.getValue(menuItemName)

    narrate("$patronName speaks with $TAVERN_MASTER to place an order")
    // Order to be placed on the basis of sufficient money
    if (itemPrice <= patronGold.getOrDefault(patronName, 0.0)) {
        val action = when (menuItemTypes[menuItemName]) {
            "shandy", "elixir" -> "pours"
            "meal" -> "serves"
            else -> "hands"
        }
        narrate("$TAVERN_MASTER $action $patronName a $menuItemName")
        narrate("$patronName pays $TAVERN_MASTER $itemPrice gold\n")
        // Deducting item's cost from patron's wallet
        patronGold[patronName] = patronGold.getValue(patronName) - itemPrice
        // Adding item's cost to Tavern master's wallet
        patronGold[TAVERN_MASTER] = patronGold.getValue(TAVERN_MASTER) + itemPrice
    } else {
        narrate("$TAVERN_MASTER says, \"You need more coin for a $menuItemName\"")
    }
}

private fun displayPatronBalances(patronGold: Map<String,Double>) {
    narrate("$heroName intuitively knows how much money each patron has")
    patronGold.forEach {(patron,balance) ->
        narrate("$patron has ${"%.2f".format(balance)} gold")
    }
}
























