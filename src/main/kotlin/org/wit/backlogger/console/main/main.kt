package org.wit.backlogger.console.main

import mu.KotlinLogging
import org.wit.backlogger.console.models.GameModel

private val logger = KotlinLogging.logger {}

//var game = GameModel()
val games = ArrayList<GameModel>()


fun main(args: Array<String>){
    logger.info { "Launching Backlogger Console App" }
    println("Backlogger Kotlin App Version 1.0")

    var input: Int

    do {
        input = menu()
        when(input) {
            1 -> addGame()
            2 -> updateGame()
            3 -> listBacklog()
            4 -> searchBacklog()
            6 -> explainApp()
            -99 -> fillWithDummy()
            -1 -> println("Exiting Application")
            else -> println("Invalid option, please try another!")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Backlogger" }
}

fun menu() : Int {

    var option : Int
    var input: String? = null

    println("~~~ Welcome to ~~~")
    println("Backlogger Main Menu")
    println("~~~~~~~~~~~~~~~~~~")
    println("Enter a number to do any of the following : ")
    println(" 1. Add a Game")
    println(" 2. Update Game")
    println(" 3. List all Games in Backlog")
    println(" 4. Search for a Game in Backlog")
    println(" 6. What is this app for?")
    println("-99. Fill the pp with dummy data")
    println("-1. Exit")
    println()
    print("Your input : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else
        -9
    return option
}

fun addGame(){
    var aGame = GameModel()
    println("Add Placemark")
    println()
    print("Enter a Title : ")
    aGame.title = readLine()!!
    print("Enter a Description : ")
    aGame.description = readLine()!!

    if (aGame.title.isNotEmpty() && aGame.description.isNotEmpty()) {
        aGame.id = games.size.toLong()
        games.add(aGame.copy())
        logger.info("Game Added : [ $aGame ]")
    }
    else
        logger.info("Game Not Added")
}

fun updateGame() {
    println("Update Placemark")
    println()
    listBacklog()
    var searchId = getId()
    val aGame = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

    if(aGame != null) {
        print("Enter a new Title for [ " + aGame.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aGame.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aGame.title = tempTitle
            aGame.description = tempDescription
            println(
                "You updated [ " + aGame.title + " ] for title " +
                        "and [ " + aGame.description + " ] for description")
                        logger.info("Game Updated : [ $aGame ]")
        }
        else
            logger.info("Game not updated")
    }
    else
        println("Game Not Updated...")
}

fun listBacklog() {
    println("List All Placemarks")
    println()
    games.forEach { logger.info("${it}") }
    println()
}

fun explainApp() {
    println("""
        Backlogger is a simple console app that allows you 
        to keep a list of games as your personal Backlog.
        
        Backlogs in general are lists of games/movies etc ( this 
        app is optimised for games ) that you don't have time to 
        enjoy yet, but you wish to try later. 
        
        With the help of the Backlogger app you can 
        better keep track of these and sort them in order of what 
        you want to play next.
        """)
}

fun searchBacklog() {

    var searchId = getId()
    val aGame = search(searchId)

    if(aGame != null)
        println("Game Details [ $aGame ]")
    else
        println("Game Not Found...")
}

fun getId() : Long {
    var strId : String? // String to hold user input
    var searchId : Long // Long to hold converted id
    print("Enter id to Search/Update : ")
    strId = readLine()!!
    searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId
}

fun search(id: Long) : GameModel? {
    var foundGame: GameModel? = games.find { p -> p.id == id }
    return foundGame
}

fun fillWithDummy() {
    games.add(GameModel(1, "GameA", "DesA"))
    games.add(GameModel(2, "GameB", "DesB"))
    games.add(GameModel(3, "GameC", "DesC"))
}