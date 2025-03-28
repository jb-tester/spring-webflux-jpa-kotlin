package org.example.demoktmv.controller

import org.example.demoktmv.service.CatHandler
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.MediaType.APPLICATION_JSON
import org.springframework.web.reactive.function.server.coRouter

@Configuration
class CatRouterConfig {
    @Bean
    fun catsRouter(catHandler: CatHandler) = coRouter {

        accept(APPLICATION_JSON).nest {
            (GET("/api/cats/") or GET("/api/cats/all")).invoke(catHandler::findAll)
            GET("/api/cats/bySound", catHandler::bySound)
        }}

}