package org.mytests.spring.service

import org.mytests.spring.model.Animal
import org.mytests.spring.repository.AnimalRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AnimalService(private val animalRepository: AnimalRepository) {
    

    fun findAll(): List<Animal> {
        return animalRepository.findAll()
    }
    

    fun findById(id: Long): Optional<Animal> {
        return animalRepository.findById(id)
    }
    

    fun findByName(name: String): List<Animal?> {
        return animalRepository.findByName(name)
    }
    

    

}
