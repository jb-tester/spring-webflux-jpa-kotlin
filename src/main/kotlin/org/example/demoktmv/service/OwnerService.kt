package org.example.demoktmv.service

import org.example.demoktmv.model.Owner
import org.example.demoktmv.repository.OwnerRepository
import org.springframework.stereotype.Service

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {
    
    /**
     * Get all animal names for an owner by ID
     * 
     * @param ownerId the ID of the owner
     * @return a list of animal names
     */
    fun getAnimalNamesByOwnerId(ownerId: Long): List<String> {
        return ownerRepository.findAnimalNamesByOwnerId(ownerId)
    }
    
    /**
     * Get all animal names for an owner by first name and last name
     * 
     * @param firstName the first name of the owner
     * @param lastName the last name of the owner
     * @return a list of animal names
     */
    fun getAnimalNamesByOwnerName(firstName: String, lastName: String): List<String> {
        return ownerRepository.findAnimalNamesByOwnerName(firstName, lastName)
    }
    
    /**
     * Get owner by ID
     * 
     * @param id the ID of the owner
     * @return the owner or null if not found
     */
    fun getOwnerById(id: Long): Owner? {
        return ownerRepository.findById(id).orElse(null)
    }
    
    /**
     * Find all owners who have both cats and dogs
     * 
     * @return a list of owners who have both cats and dogs
     */
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
