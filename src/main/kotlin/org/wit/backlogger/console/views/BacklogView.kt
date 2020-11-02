package org.wit.backlogger.console.views

import org.wit.backlogger.console.models.BackloggerJSONStore
//import org.wit.backlogger.console.main.BacklogView
//import org.wit.backlogger.console.main.games
import org.wit.backlogger.console.models.BacklogMemStore
import org.wit.backlogger.console.models.GameModel

class BacklogView {

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
        println(" 5. Remove a Game from Backlog")
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

    fun listBacklog(games : BackloggerJSONStore) {
        println("List all Games in Backlog")
        println()
        games.logAll()
        println()
    }

    fun showGame(game : GameModel) {
        if(game != null)
            println("Game Details [ $game ]")
        else
            println("Placemark Not Found...")
    }

    fun addGameData(game : GameModel) : Boolean {

        println()
        print("Enter a Title : ")
        game.title = readLine()!!
        print("Enter a Description : ")
        game.description = readLine()!!

        return game.title.isNotEmpty() && game.description.isNotEmpty()
    }

    fun updateGameData(game : GameModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?

        if (game != null) {
            print("Enter a new Title for [ " + game.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new Description for [ " + game.description + " ] : ")
            tempDescription = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                game.title = tempTitle
                game.description = tempDescription
                return true
            }
        }
        return false
    }

    fun removeGame(game : GameModel) : Boolean {


        if (game != null) {

//            game.remove(game)
            return true
        }
        else
        {
            return false
        }
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
}