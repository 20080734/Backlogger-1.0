package org.wit.backlogger.console.views

import org.wit.backlogger.console.models.BackloggerJSONStore
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
            println("Game Not Found...")
    }

    fun addGameData(game : GameModel) : Boolean {

        println()
        print("Enter the game title : ")
        game.title = readLine()!!
        print("Enter a description : ")
        game.description = readLine()!!
        print("Enter the game's developer : ")
        game.developer = readLine()!!
        print("Enter the game's publisher : ")
        game.publisher = readLine()!!
        print("Enter the game's releaseDate : ")
        game.releaseDate = readLine()!!
        print("Enter the game's platform : ")
        game.platform = readLine()!!
        print("Enter the game's genre : ")
        game.genre = readLine()!!
        print("Enter the game's Metacritic score: ")
        game.metacritic = readLine()!!
        print("Enter a link to the game's cover art : ")
        game.coverArt = readLine()!!

        return game.title.isNotEmpty() && game.description.isNotEmpty() && game.developer.isNotEmpty() && game.publisher.isNotEmpty() && game.releaseDate.isNotEmpty() && game.platform.isNotEmpty() && game.genre.isNotEmpty() && game.metacritic.isNotEmpty() && game.coverArt.isNotEmpty()
    }

    fun updateGameData(game : GameModel) : Boolean {

        var tempTitle: String?
        var tempDescription: String?
        var tempDeveloper: String?
        var tempPublisher: String?
        var tempReleaseDate: String?
        var tempPlatform: String?
        var tempGenre: String?
        var tempMetacritic: String?
        var tempCoverArt: String?

        if (game != null) {
            print("Enter a new title for [ " + game.title + " ] : ")
            tempTitle = readLine()!!
            print("Enter a new description to replace [ " + game.description + " ] : ")
            tempDescription = readLine()!!
            print("Enter a new developer to replace [ " + game.developer + " ] : ")
            tempDeveloper = readLine()!!
            print("Enter a new publisher to replace [ " + game.publisher + " ] : ")
            tempPublisher = readLine()!!
            print("Enter a new release date to replace [ " + game.releaseDate + " ] : ")
            tempReleaseDate = readLine()!!
            print("Enter a new platform to replace [ " + game.platform + " ] : ")
            tempPlatform = readLine()!!
            print("Enter a new genre to replace [ " + game.genre + " ] : ")
            tempGenre = readLine()!!
            print("Enter a new Metacritic score to replace [ " + game.metacritic + " ] : ")
            tempMetacritic = readLine()!!
            print("Enter a new cover art link to replace [ " + game.coverArt + " ] : ")
            tempCoverArt = readLine()!!

            if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
                game.title = tempTitle
                game.description = tempDescription
                game.developer = tempDeveloper
                game.publisher = tempPublisher
                game.releaseDate = tempReleaseDate
                game.platform = tempPlatform
                game.genre = tempGenre
                game.metacritic = tempMetacritic
                game.coverArt = tempCoverArt
                return true
            }
        }
        return false
    }

    fun removeGame(game : GameModel) : Boolean {
        if (game != null) {
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