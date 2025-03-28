package org.example.demoktmv.service

import org.example.demoktmv.model.Animal
import org.example.demoktmv.repository.AnimalRepository
import org.springframework.stereotype.Service
import java.util.Optional

@Service
class AnimalService(private val animalRepository: AnimalRepository) {
    
    /**
     * Find all animals
     */
    fun findAll(): List<Animal> {
        return animalRepository.findAll()
    }
    
    /**
     * Find animal by ID
     */
    fun findById(id: Long): Optional<Animal> {
        return animalRepository.findById(id)
    }
    
    /**
     * Find animal by name
     */
    fun findByName(name: String): List<Animal?> {
        return animalRepository.findByName(name)
    }
    

    

}
