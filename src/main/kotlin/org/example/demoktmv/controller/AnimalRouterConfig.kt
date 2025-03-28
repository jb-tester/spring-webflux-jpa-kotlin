package org.example.demoktmv.controller

import org.example.demoktmv.service.AnimalHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class AnimalRouterConfig {

    @Bean
    fun animalRoutes(animalHandler: AnimalHandler) = coRouter {
        GET("/animals/all", animalHandler::findAll)
        GET("/animals/{id}", animalHandler::findOne)
        GET("/animals/byName/{name}", animalHandler::findByName)
    }
}