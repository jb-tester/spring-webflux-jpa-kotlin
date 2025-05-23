package org.example.demoktmv.service

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.reactive.asPublisher
import org.example.demoktmv.model.Animal
import org.example.demoktmv.repository.AnimalRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class AnimalService(private val animalRepository: AnimalRepository) {
    
    /**
     * Find all animals as Flow
     */
    suspend fun findAllAsFlow(): Flow<Animal> {
        return animalRepository.findAll()
    }
    
    /**
     * Find all animals as Flux
     */
    fun findAll(): Flux<Animal> {
        return Flux.from(animalRepository.findAll().asPublisher())
    }
    
    /**
     * Find animal by ID
     */
    suspend fun findByIdSuspend(id: Long): Animal? {
        return animalRepository.findById(id)
    }
    
    /**
     * Find animal by ID as Mono
     */
    suspend fun findById(id: Long): Mono<Animal?> {
        return Mono.justOrEmpty(animalRepository.findById(id))

    }
    
    /**
     * Find animal by name as list
     */
    suspend fun findByNameAsList(name: String): Flux<Animal?> {
        return animalRepository.findByName(name)
    }
    
    /**
     * Find animal by name as Flux
     */
    suspend fun findByName(name: String): Flux<Animal?> {
        return animalRepository.findByName(name)
    }
}
