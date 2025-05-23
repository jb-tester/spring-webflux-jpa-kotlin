package org.example.demoktmv.service

import org.example.demoktmv.model.Owner
import org.example.demoktmv.repository.OwnerRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class OwnerService(private val ownerRepository: OwnerRepository) {
    
    /**
     * Get all animal names for an owner by ID
     * 
     * @param ownerId the ID of the owner
     * @return a list of animal names
     */
    suspend fun getAnimalNamesByOwnerId(ownerId: Long): Flux<String> {
        return ownerRepository.findAnimalNamesByOwnerId(ownerId)
    }
    
    /**
     * Get all animal names for an owner by first name and last name
     * 
     * @param firstName the first name of the owner
     * @param lastName the last name of the owner
     * @return a list of animal names
     */
    suspend fun getAnimalNamesByOwnerName(firstName: String, lastName: String): Flux<String> {
        return ownerRepository.findAnimalNamesByOwnerName(firstName, lastName)
    }
    
    /**
     * Get owner by ID
     * 
     * @param id the ID of the owner
     * @return the owner or null if not found
     */
    suspend fun getOwnerById(id: Long): Mono<Owner?> {
        return Mono.justOrEmpty(ownerRepository.findById(id))
    }
    
    /**
     * Find all owners who have both cats and dogs
     * 
     * @return a list of owners who have both cats and dogs
     */
    suspend fun findOwnersWithBothCatAndDog(): Flux<Owner?> {
        val owners = ownerRepository.findOwnersWithBothCatAndDog()
        return owners
    }
    

}
