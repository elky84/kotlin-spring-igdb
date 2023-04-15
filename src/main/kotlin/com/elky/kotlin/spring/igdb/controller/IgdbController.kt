package com.elky.kotlin.spring.igdb.controller

import com.elky.kotlin.spring.igdb.dto.GameDto
import com.elky.kotlin.spring.igdb.dto.GameListDto
import com.elky.kotlin.spring.igdb.dto.GameListReqDto
import com.elky.kotlin.spring.igdb.service.IgdbService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.*

@Controller
@RequestMapping("/igdb")
class IgdbController(val igdbService: IgdbService) {

    @RequestMapping(method = [RequestMethod.GET], path = ["/game/{gameId}"])
    @ResponseBody
    fun game(@PathVariable gameId: Long) : GameDto? {
        val game = igdbService.game(gameId) ?: return null
        return GameDto(game)
    }

    @RequestMapping(method = [RequestMethod.GET], path = ["/game"])
    @ResponseBody
    fun game(gameListReqDto: GameListReqDto) : GameListDto {

        val list = gameListReqDto.gameIds.chunked(10).mapNotNull { chunk ->
            igdbService.games("id = (${chunk.joinToString(",")})")?.map { GameDto(it) }
        }.flatten()

        return GameListDto(list)
    }
}