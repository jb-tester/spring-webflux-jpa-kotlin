package org.example.demoktmv.service

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.json

@Component
class AnimalHandler(private val animalService: AnimalService) {

    /**
     * Find all animals
     */
    suspend fun findAll(request: ServerRequest): ServerResponse {
        val animals = animalService.findAll()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .json()
            .bodyValueAndAwait(animals)
    }

    /**
     * Find animal by ID
     */
    suspend fun findOne(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val animal = animalService.findById(id)
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .json()
                .bodyValueAndAwait(animal)
    }

    /**
     * Find animal by name
     */
    suspend fun findByName(request: ServerRequest): ServerResponse {
        val name = request.pathVariable("name")
        val animals = animalService.findByName(name)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .json()
            .bodyValueAndAwait(animals)
    }
}
