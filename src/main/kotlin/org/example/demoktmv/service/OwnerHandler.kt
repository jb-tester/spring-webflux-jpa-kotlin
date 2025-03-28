package org.example.demoktmv.service

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyValueAndAwait
import org.springframework.web.reactive.function.server.buildAndAwait

@Component
class OwnerHandler(private val ownerService: OwnerService) {

    /**
     * Get animal names by owner ID
     */
    suspend fun getAnimalNamesByOwnerId(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val animalNames = ownerService.getAnimalNamesByOwnerId(id)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(animalNames)
    }

    /**
     * Get animal names by owner's first name and last name
     */
    suspend fun getAnimalNamesByOwnerName(request: ServerRequest): ServerResponse {
        val firstName = request.queryParam("firstName").orElse("")
        val lastName = request.queryParam("lastName").orElse("")

        val animalNames = ownerService.getAnimalNamesByOwnerName(firstName, lastName)
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(animalNames)
    }

    /**
     * Get owner by ID
     */
    suspend fun getOwnerById(request: ServerRequest): ServerResponse {
        val id = request.pathVariable("id").toLong()
        val owner = ownerService.getOwnerById(id)

        return if (owner != null) {
            ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .bodyValueAndAwait(owner)
        } else {
            ServerResponse.notFound().buildAndAwait()
        }
    }

    /**
     * Get all owners who have both cats and dogs
     */
    suspend fun getOwnersWithBothCatAndDog(request: ServerRequest): ServerResponse {
        val owners = ownerService.findOwnersWithBothCatAndDog()
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(owners)
    }

    /**
     * Get names of owners who have both cats and dogs
     */
    suspend fun getOwnerNamesWithBothCatAndDog(request: ServerRequest): ServerResponse {
        val ownerNames = ownerService.findOwnersWithBothCatAndDog().map { it.getFullName() }
        return ServerResponse.ok()
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValueAndAwait(ownerNames)
    }
}