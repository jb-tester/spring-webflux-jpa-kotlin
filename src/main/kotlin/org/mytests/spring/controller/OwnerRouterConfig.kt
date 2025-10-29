package org.mytests.spring.controller

import org.mytests.spring.service.OwnerHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType
import org.springframework.web.reactive.function.server.RouterFunction
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class OwnerRouterConfig(private val ownerHandler: OwnerHandler) {

    @Bean
    fun ownerRoutes(): RouterFunction<ServerResponse> = coRouter {
        "/api/owners".nest {
            accept(MediaType.APPLICATION_JSON).nest {
                GET("/{id}/animal-names", ownerHandler::getAnimalNamesByOwnerId)

                GET("/animal-names", ownerHandler::getAnimalNamesByOwnerName)

                GET("/by{id}", ownerHandler::getOwnerById)

                GET("/with-both-cat-and-dog", ownerHandler::getOwnersWithBothCatAndDog)

                GET("/with-both-cat-and-dog/names", ownerHandler::getOwnerNamesWithBothCatAndDog)
            }
        }
    }


    }
