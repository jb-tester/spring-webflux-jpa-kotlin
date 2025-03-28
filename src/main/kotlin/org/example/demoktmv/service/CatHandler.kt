package org.example.demoktmv.service

import kotlinx.coroutines.reactor.awaitSingle
import org.example.demoktmv.model.Cat
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyToMono
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class CatHandler(private val catService: CatService, private val ownerService: OwnerService) {

    /**
     * Find all cats
     */
    suspend fun findAll(request: ServerRequest): ServerResponse {
        val cats = catService.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .json()
            .bodyValueAndAwait(cats)
    }
    suspend fun bySound(request: ServerRequest): ServerResponse {
        val cats = catService.bySound()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .json()
            .bodyValueAndAwait(cats)
    }



}

