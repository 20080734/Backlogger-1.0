package org.wit.backlogger.console.models
import org.wit.backlogger.console.helpers.*

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import mu.KotlinLogging

import java.util.*

private val logger = KotlinLogging.logger {}

const val CON_RED="\u001b[31m"
const val CON_CLEAR="\u001b[0m"
const val CON_GREEN="\u001b[32m"
const val CON_CYAN="\u001b[36m"



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
        //games.forEach { logger.info("${it}")}
        games.forEach { println(CON_RED+"================================="+CON_CLEAR)
                        println(CON_RED+"¦ "+CON_GREEN+it.title+CON_CLEAR)
                        println(CON_RED+"------------------------------"+CON_CLEAR)
                        println(CON_RED+"¦"+CON_CYAN+" Description : "+CON_CLEAR + it.description)
                        println(CON_RED+"¦"+CON_CYAN+" Developer : "+CON_CLEAR + it.developer)
                        println(CON_RED+"¦"+CON_CYAN+" Publisher : "+CON_CLEAR + it.publisher)
                        println(CON_RED+"¦"+CON_CYAN+" Release Date : "+CON_CLEAR + it.releaseDate)
                        println(CON_RED+"¦"+CON_CYAN+" Platform : "+CON_CLEAR + it.platform)
                        println(CON_RED+"¦"+CON_CYAN+" Genre : "+CON_CLEAR + it.genre)
                        println(CON_RED+"¦"+CON_CYAN+" Metacritic Score : "+CON_CLEAR + it.metacritic)
                        println(CON_RED+"¦"+CON_CYAN+" Link To Cover Art : "+CON_CLEAR + it.coverArt)
                        println(CON_RED+"¦"+CON_CYAN+" ID : "+CON_CLEAR + it.id)
                        println(CON_RED+"================================="+CON_CLEAR)
                        println("\n")}
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