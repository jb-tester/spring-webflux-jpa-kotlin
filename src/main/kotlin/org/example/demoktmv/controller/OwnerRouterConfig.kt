package org.example.demoktmv.controller

import org.example.demoktmv.service.AnimalHandler
import org.example.demoktmv.service.CatHandler
import org.example.demoktmv.service.OwnerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class OwnerRouterConfig(private val ownerHandler: OwnerHandler) {

    @Bean
    fun ownerRoutes(): RouterFunction<ServerResponse> = coRouter {
        "/api/owners".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                // Get animal names by owner ID
                GET("/{id}/animal-names", ownerHandler::getAnimalNamesByOwnerId)

                // Get animal names by owner name
                GET("/animal-names", ownerHandler::getAnimalNamesByOwnerName)

                // Get owner by ID
                GET("/by{id}", ownerHandler::getOwnerById)

                // Get owners with both cat and dog
                GET("/with-both-cat-and-dog", ownerHandler::getOwnersWithBothCatAndDog)

                // Get names of owners who have both cat and dog
                GET("/with-both-cat-and-dog/names", ownerHandler::getOwnerNamesWithBothCatAndDog)
            }
        }
    }


    }
