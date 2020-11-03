package org.wit.backlogger.console.views

import org.wit.backlogger.console.models.BackloggerJSONStore
import org.wit.backlogger.console.models.GameModel

class BacklogView {

    //Colours for console
    val CON_RED="\u001b[31m"
    val CON_CLEAR="\u001b[0m"
    val CON_GREEN="\u001b[32m"
    val CON_CYAN="\u001b[36m"

    //Menu View
    fun menu() : Int {
        var option : Int
        var input: String? = null

        println(CON_RED+"~~~ Welcome to ~~~"+CON_CLEAR)
        println("Backlogger Main Menu"+CON_CLEAR)
        println(CON_RED+"~~~~~~~~~~~~~~~~~~"+CON_CLEAR)
        println("Enter a number to do any of the following : "+CON_CLEAR)
        println(" 1. "+CON_CYAN+"Add a Game"+CON_CLEAR)
        println(" 2. "+CON_CYAN+"Update Game"+CON_CLEAR)
        println(" 3. "+CON_CYAN+"List all Games in Backlog"+CON_CLEAR)
        println(" 4. "+CON_CYAN+"Search for a Game in Backlog"+CON_CLEAR)
        println(" 5. "+CON_CYAN+"Remove a Game from Backlog"+CON_CLEAR)
        println(" 6. "+CON_CYAN+"What is this app for?"+CON_CLEAR)
        println("-1. "+CON_CYAN+"Exit"+CON_CLEAR)
        println(CON_RED+"~~~~~~~~~~~~~~~~~~"+CON_CLEAR)
        println()
        print(CON_GREEN+"Your input : "+CON_CLEAR)
        input = readLine()!!
        option = if (input.toIntOrNull() != null && !input.isEmpty())
            input.toInt()
        else
            -9
        return option
    }

    //Prints existing games in backlog
    fun listBacklog(games : BackloggerJSONStore) {
        println(CON_RED+"Listing all Games in Backlog..."+CON_CLEAR)
        println()
        games.logAll()
        println()
    }

    //Displays information on a single game
    fun showGame(game : GameModel) {
        if(game != null){
            println("Game Details [ $game ]")
            println(CON_RED+"================================="+CON_CLEAR)
            println(CON_RED+"¦ "+CON_GREEN+game.title+CON_CLEAR)
            println(CON_RED+"------------------------------"+CON_CLEAR)
            println(CON_RED+"¦"+CON_CYAN+" Description : "+CON_CLEAR + game.description)
            println(CON_RED+"¦"+CON_CYAN+" Developer : "+CON_CLEAR + game.developer)
            println(CON_RED+"¦"+CON_CYAN+" Publisher : "+CON_CLEAR + game.publisher)
            println(CON_RED+"¦"+CON_CYAN+" Release Date : "+CON_CLEAR + game.releaseDate)
            println(CON_RED+"¦"+CON_CYAN+" Platform : "+CON_CLEAR + game.platform)
            println(CON_RED+"¦"+CON_CYAN+" Genre : "+CON_CLEAR + game.genre)
            println(CON_RED+"¦"+CON_CYAN+" Metacritic Score : "+CON_CLEAR + game.metacritic)
            println(CON_RED+"¦"+CON_CYAN+" Link To Cover Art : "+CON_CLEAR + game.coverArt)
            println(CON_RED+"¦"+CON_CYAN+" ID : "+CON_CLEAR + game.id)
            println(CON_RED+"================================="+CON_CLEAR)
            println("\n")}
        else{
            println(CON_RED+"Game Not Found..."+CON_CLEAR)
        }
    }

    //Displays inputs needed for adding a game
    fun addGameData(game : GameModel) : Boolean {

        println()
        print(CON_GREEN+"Enter the game title : "+CON_CLEAR)
        game.title = readLine()!!
        print(CON_GREEN+"Enter a description : "+CON_CLEAR)
        game.description = readLine()!!
        print(CON_GREEN+"Enter the game's developer : "+CON_CLEAR)
        game.developer = readLine()!!
        print(CON_GREEN+"Enter the game's publisher : "+CON_CLEAR)
        game.publisher = readLine()!!
        print(CON_GREEN+"Enter the game's releaseDate : "+CON_CLEAR)
        game.releaseDate = readLine()!!
        print(CON_GREEN+"Enter the game's platform : "+CON_CLEAR)
        game.platform = readLine()!!
        print(CON_GREEN+"Enter the game's genre : "+CON_CLEAR)
        game.genre = readLine()!!
        print(CON_GREEN+"Enter the game's Metacritic score: "+CON_CLEAR)
        game.metacritic = readLine()!!
        print(CON_GREEN+"Enter a link to the game's cover art : "+CON_CLEAR)
        game.coverArt = readLine()!!

        return game.title.isNotEmpty() && game.description.isNotEmpty() && game.developer.isNotEmpty() && game.publisher.isNotEmpty() && game.releaseDate.isNotEmpty() && game.platform.isNotEmpty() && game.genre.isNotEmpty() && game.metacritic.isNotEmpty() && game.coverArt.isNotEmpty()
    }

    //Displays inputs for updating a game
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
            print(CON_GREEN+"Enter a new title for [ " +CON_CYAN+ game.title +CON_GREEN+ " ] : "+CON_CLEAR)
            tempTitle = readLine()!!
            print(CON_GREEN+"Enter a new description to replace [ " +CON_CYAN+ game.description +CON_GREEN+ " ] : "+CON_CLEAR)
            tempDescription = readLine()!!
            print(CON_GREEN+"Enter a new developer to replace [ " +CON_CYAN+ game.developer +CON_GREEN+ " ] : "+CON_CLEAR)
            tempDeveloper = readLine()!!
            print(CON_GREEN+"Enter a new publisher to replace [ " +CON_CYAN+ game.publisher +CON_GREEN+ " ] : "+CON_CLEAR)
            tempPublisher = readLine()!!
            print(CON_GREEN+"Enter a new release date to replace [ " +CON_CYAN+ game.releaseDate +CON_GREEN+ " ] : "+CON_CLEAR)
            tempReleaseDate = readLine()!!
            print(CON_GREEN+"Enter a new platform to replace [ " +CON_CYAN+ game.platform +CON_GREEN+ " ] : "+CON_CLEAR)
            tempPlatform = readLine()!!
            print(CON_GREEN+"Enter a new genre to replace [ " +CON_CYAN+ game.genre +CON_GREEN+ " ] : "+CON_CLEAR)
            tempGenre = readLine()!!
            print(CON_GREEN+"Enter a new Metacritic score to replace [ " +CON_CYAN+ game.metacritic +CON_GREEN+ " ] : "+CON_CLEAR)
            tempMetacritic = readLine()!!
            print(CON_GREEN+"Enter a new cover art link to replace [ " +CON_CYAN+ game.coverArt +CON_GREEN+ " ] : "+CON_CLEAR)
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

    //Displays nothing, just used to complete the Delete function
    fun removeGame(game : GameModel) : Boolean {
        if (game != null) {
            return true
        }
        else
        {
            return false
        }
    }

    //Displays request for ID
    fun getId() : Long {
        var strId : String?
        var searchId : Long
        print(CON_GREEN+"Enter game id to continue : "+CON_CLEAR)
        strId = readLine()!!
        searchId = if (strId.toLongOrNull() != null && !strId.isEmpty())
            strId.toLong()
        else
            -9
        return searchId
    }
}