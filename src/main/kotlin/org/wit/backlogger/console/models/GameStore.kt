package org.wit.backlogger.console.models

interface GameStore {
    fun findAll(): List<GameModel>
    fun findOne(id: Long): GameModel?
    fun create(game: GameModel)
    fun update(game: GameModel)
}