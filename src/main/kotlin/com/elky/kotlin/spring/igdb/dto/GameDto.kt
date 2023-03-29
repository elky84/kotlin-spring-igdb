package com.elky.kotlin.spring.igdb.dto

import proto.Game

data class GameDto(val id: Long, val name: String) {
    constructor(game: Game) : this(game.id, game.name)
}