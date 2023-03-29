package com.elky.kotlin.spring.igdb.controller

import com.elky.kotlin.spring.igdb.dto.GameDto
import com.elky.kotlin.spring.igdb.service.IgdbService
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import org.springframework.web.bind.annotation.ResponseBody

@Controller
@RequestMapping("/igdb")
class IgdbController(val igdbService: IgdbService) {

    @RequestMapping(method = [RequestMethod.GET], path = ["/game/{gameId}"])
    @ResponseBody
    fun game(@PathVariable gameId: Long) : GameDto? {
        val game = igdbService.game(gameId) ?: return null
        return GameDto(game)
    }
}