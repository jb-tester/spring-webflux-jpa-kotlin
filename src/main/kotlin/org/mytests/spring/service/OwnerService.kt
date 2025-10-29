package org.mytests.spring.service

import org.mytests.spring.model.Owner
import org.mytests.spring.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {
    

    fun getAnimalNamesByOwnerId(ownerId: Long): List<String> {
        return ownerRepository.findAnimalNamesByOwnerId(ownerId)
    }
    

    fun getAnimalNamesByOwnerName(firstName: String, lastName: String): List<String> {
        return ownerRepository.findAnimalNamesByOwnerName(firstName, lastName)
    }
    

    fun getOwnerById(id: Long): Owner? {
        return ownerRepository.findById(id).orElse(null)
    }
    

    fun findOwnersWithBothCatAndDog(): List<Owner> {
        val owners = ownerRepository.findOwnersWithBothCatAndDog()
        return owners
    }
    
    /**
     * Alternative implementation
     */
    fun findOwnersWithBothCatAndDogInMemory(): List<Owner> {
        return ownerRepository.findAll().filter { it.hasBothCatAndDog() }
    }
}
