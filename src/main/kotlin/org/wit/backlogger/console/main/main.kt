package org.wit.backlogger.console.main

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}

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
            6 -> explainApp()
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
    println(" 6. What is this app for?")
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
    println("You Chose to add a Game to Backlog")
}

fun updateGame() {
    println("You chose to update a Game currently in Backlog")
}

fun listBacklog() {
    println("You chose to list all Games in Backlog")
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