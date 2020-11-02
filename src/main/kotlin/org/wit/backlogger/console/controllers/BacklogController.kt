package org.wit.backlogger.console.controllers

import mu.KotlinLogging
import org.wit.backlogger.console.models.BackloggerJSONStore
import org.wit.backlogger.console.models.GameModel
import org.wit.backlogger.console.views.BacklogView

class BacklogController {

    val games = BackloggerJSONStore()
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
        games.create(GameModel(3441221404404550687, "Counter Strike", "Description for CS","Valve","Valve","1999","PC","FPS","88","www.coverartimage.com"))
        games.create(GameModel(4551221404401640687, "Half Life", "Description for HL","Valve","Valve","1998","PC","FPS","96","www.coverartimage.com"))
        games.create(GameModel(7777221404401640687, "Dark Souls", "Description for DS","From Software","Bandai Namco","2010","PS3","RPG","97","www.coverartimage.com"))
        games.create(GameModel(7577221404401640687, "Metal Gear Solid", "Description for MGS","Fox Team","Konami","1998","PS1","Action","99","www.coverartimage.com"))

    }
}