package org.wit.backlogger.console.models
import org.wit.backlogger.console.helpers.*

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import java.util.*

private val logger = KotlinLogging.logger {}

val JSON_FILE = "games.json"
val gsonBuilder = GsonBuilder().setPrettyPrinting().create()
val listType = object : TypeToken<java.util.ArrayList<GameModel>>() {}.type

fun generateRandomId(): Long {
    return Random().nextLong()
}


class BackloggerJSONStore : GameStore {

    var games = mutableListOf<GameModel>()

    init {
        if (exists(JSON_FILE)) {
            deserialize()
        }
    }

    override fun findAll(): MutableList<GameModel> {
        return games
    }

    override fun findOne(id: Long) : GameModel? {
        var foundGame: GameModel? = games.find { g -> g.id == id }
        return foundGame
    }

    override fun create(game: GameModel) {
        game.id = generateRandomId()
        games.add(game)
        serialize()
    }

    override fun update(game: GameModel) {
        var foundGame = findOne(game.id!!)
        if (foundGame != null) {
            foundGame.title = game.title
            foundGame.description = game.description
            foundGame.developer = game.developer
            foundGame.publisher = game.publisher
            foundGame.releaseDate = game.releaseDate
            foundGame.platform = game.platform
            foundGame.genre = game.genre
            foundGame.metacritic = game.metacritic
            foundGame.coverArt = game.coverArt
        }
        serialize()
    }

    override fun remove(game: GameModel) {
        var foundGame = findOne(game.id!!)
        if (foundGame != null) {
            games.remove(game)
        }
        serialize()
    }

    internal fun logAll() {
        games.forEach { logger.info("${it}") }
    }

    private fun serialize() {
        val jsonString = gsonBuilder.toJson(games, listType)
        write(JSON_FILE, jsonString)
    }

    private fun deserialize() {
        val jsonString = read(JSON_FILE)
        games = Gson().fromJson(jsonString, listType)
    }
}