package org.wit.backlogger.console.models

import mu.KotlinLogging

private val logger = KotlinLogging.logger {}
var lastId = 0L

internal fun getId(): Long {
    return lastId++
}

class BacklogMemStore : GameStore {

    val games = ArrayList<GameModel>()

    override fun findAll(): List<GameModel> {
        return games
    }

    override fun findOne(id: Long) : GameModel? {
        var foundGame: GameModel? = games.find { g -> g.id == id }
        return foundGame
    }

    override fun create(game: GameModel) {
        game.id = getId()
        games.add(game)
        logAll()
    }

    override fun update(game: GameModel) {
        var foundGame = findOne(game.id!!)
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.description = game.description
        }
    }

    override fun remove(game: GameModel) {
        var foundGame = findOne(game.id!!)
        if (foundGame != null) {
            games.remove(game)
        }
    }

    internal fun logAll() {
        games.forEach { logger.info("${it}") }
    }
}