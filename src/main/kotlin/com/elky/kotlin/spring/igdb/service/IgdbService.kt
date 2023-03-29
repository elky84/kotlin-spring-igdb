package com.elky.kotlin.spring.igdb.service

import com.api.igdb.apicalypse.APICalypse
import com.api.igdb.apicalypse.Sort
import com.api.igdb.exceptions.RequestException
import com.api.igdb.request.*
import jakarta.annotation.PostConstruct
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service
import proto.*

@Service
class IgdbService {

    @Value("\${igdb.client-id}")
    private lateinit var clientId: String

    @Value("\${igdb.client-secret}")
    private lateinit var clientSecret: String

    @PostConstruct
    private fun postConstruct() {
        val token = TwitchAuthenticator.requestTwitchToken(clientId, clientSecret)
        IGDBWrapper.setCredentials(clientId, token!!.access_token)
    }


    fun video(id: Long): List<GameVideo> {
        val apiCalypse = APICalypse().fields("*").where("id = $id")
        return try{
            IGDBWrapper.gameVideos(apiCalypse)
        } catch(e: RequestException) {
            println(e.message)
            emptyList()
        } catch(e: RuntimeException) {
            println(e.message)
            emptyList()
        }
    }

    fun artwork(id: Long): List<Artwork> {
        val apiCalypse = APICalypse().fields("*").where("id = $id")
        return try{
            IGDBWrapper.artworks(apiCalypse)
        } catch(e: RequestException) {
            println(e.message)
            emptyList()
        } catch(e: RuntimeException) {
            println(e.message)
            emptyList()
        }
    }

    fun cover(id: Long): List<Cover> {
        val apiCalypse = APICalypse().fields("*").where("id = $id")
        return try{
            IGDBWrapper.covers(apiCalypse)
        } catch(e: RequestException) {
            println(e.message)
            emptyList()
        } catch(e: RuntimeException) {
            println(e.message)
            emptyList()
        }
    }

    fun search(query: String): List<Search>? {
        val apiCalypse = APICalypse().search(query).fields("*")
        return try{
            IGDBWrapper.search(apiCalypse)
        } catch(e: RequestException) {
            println(e.message)
            null
        } catch(e: RuntimeException) {
            println(e.message)
            null
        }
    }

    fun games(query: String) : List<Game>? {
        val apiCalypse = APICalypse().fields("*").sort("release_dates.date", Sort.DESCENDING)
            .where(query)
        return try{
            IGDBWrapper.games(apiCalypse)

        } catch(e: RequestException) {
            println(e.message)
            null
        } catch(e: RuntimeException) {
            println(e.message)
            null
        }
    }

    fun game(id: Long) : Game? {
        val apiCalypse = APICalypse().fields("*").where("id = $id")
        return try{
            IGDBWrapper.games(apiCalypse)[0]
        } catch(e: RequestException) {
            println(e.message)
            null
        } catch(e: RuntimeException) {
            println(e.message)
            null
        }
    }
}