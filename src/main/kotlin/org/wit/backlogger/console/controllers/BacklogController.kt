package org.wit.backlogger.console.controllers

import mu.KotlinLogging
import org.wit.backlogger.console.models.BacklogMemStore
import org.wit.backlogger.console.models.BackloggerJSONStore
import org.wit.backlogger.console.models.GameModel
import org.wit.backlogger.console.views.BacklogView

class BacklogController {

    val games = BackloggerJSONStore()
    //val games = BacklogMemStore()
    val backlogView = BacklogView()
    val logger = KotlinLogging.logger {}

    init {
        logger.info { "Launching Backlogger Console App" }
        println("Backlogger Kotlin App Version 1.0")
    }

    fun start() {
        var input: Int

        do {
            input = menu()
            when (input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                5 -> remove()
                6 -> explainApp()
                -99 -> fillWithDummy()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        logger.info { "Shutting Down Backlogger Console App" }
    }

    fun menu() :Int { return backlogView.menu() }

    fun add(){
        var aGame = GameModel()

        if (backlogView.addGameData(aGame))
            games.create(aGame)
        else
            logger.info("Game Not Added")
    }

    fun list() {
        backlogView.listBacklog(games)
    }

    fun update() {

        backlogView.listBacklog(games)
        var searchId = backlogView.getId()
        val aGame = search(searchId)

        if(aGame != null) {
            if(backlogView.updateGameData(aGame)) {
                games.update(aGame)
                backlogView.showGame(aGame)
                logger.info("Game Updated : [ $aGame ]")
            }
            else
                logger.info("Game Not Updated")
        }
        else
            println("Game Not Updated...")
    }

    fun remove() {
        backlogView.listBacklog(games)
        var searchId = backlogView.getId()
        val aGame = search(searchId)

        if(aGame != null) {
            if(backlogView.removeGame(aGame)) {
                games.remove(aGame)
                backlogView.showGame(aGame)
                logger.info("Game Deleted : [ $aGame ]")
            }
            else
                logger.info("Game Not Deleted")
        }
        else
            println("Game Not Deleted...")
    }

    fun search() {
        val aGame = search(backlogView.getId())!!
        backlogView.showGame(aGame)
    }


    fun search(id: Long) : GameModel? {
        var foundGame = games.findOne(id)
        return foundGame
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

    fun fillWithDummy() {
        games.create(GameModel(1, "GameA", "DesA"))
        games.create(GameModel(2, "GameB", "DesB"))
        games.create(GameModel(3, "GameC", "DesC"))
    }
}