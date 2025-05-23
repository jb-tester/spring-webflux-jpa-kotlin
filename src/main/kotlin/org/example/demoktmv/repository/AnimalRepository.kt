package org.example.demoktmv.repository

import org.example.demoktmv.model.Animal
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface AnimalRepository : CoroutineCrudRepository<Animal, Long> {
    suspend fun findByName(name: String): Flux<Animal?>
}
