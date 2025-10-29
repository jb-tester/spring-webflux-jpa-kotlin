package org.example.demoktmv.service

import org.example.demoktmv.model.Animal
import org.example.demoktmv.repository.AnimalRepository
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
