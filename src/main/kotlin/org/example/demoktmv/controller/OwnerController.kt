package org.example.demoktmv.controller

import org.example.demoktmv.model.Owner
import org.example.demoktmv.service.OwnerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import reactor.core.publisher.Flux

/**
 * This controller is deprecated and will be removed in future versions.
 * Use the functional endpoint approach with OwnerRouterConfig and OwnerHandler instead.
 */
@Deprecated("Use functional endpoints with OwnerHandler instead")
@RestController
@RequestMapping("/api/deprecated/owners")
class OwnerController(private val ownerService: OwnerService) {
    
    /**
     * Get animal names by owner ID
     * 
     * @param id the ID of the owner
     * @return a list of animal names
     */
    @GetMapping("/{id}/animal-names")
    suspend fun getAnimalNamesByOwnerId(@PathVariable id: Long): ResponseEntity<Flux<String>> {
        val animalNames = ownerService.getAnimalNamesByOwnerId(id)
        return ResponseEntity.ok(animalNames)
    }
    
    /**
     * Get animal names by owner's first name and last name
     * 
     * @param firstName the first name of the owner
     * @param lastName the last name of the owner
     * @return a list of animal names
     */
    @GetMapping("/animal-names")
    suspend fun getAnimalNamesByOwnerName(
        @RequestParam firstName: String,
        @RequestParam lastName: String
    ): ResponseEntity<Flux<String>> {
        val animalNames = ownerService.getAnimalNamesByOwnerName(firstName, lastName)
        return ResponseEntity.ok(animalNames)
    }
    
    /**
     * Get owner by ID
     * 
     * @param id the ID of the owner
     * @return the owner or 404 if not found
     */
    @GetMapping("/by{id}")
    suspend fun getOwnerById(@PathVariable id: Long): ResponseEntity<Owner?> {
        val owner = ownerService.getOwnerById(id)
        return (if (owner != null) {
            ResponseEntity.ok(owner)
        } else {
            ResponseEntity.notFound().build()
        }) as ResponseEntity<Owner?>
    }
    
    /**
     * Get all owners who have both cats and dogs
     * 
     * @return a list of owners who have both cats and dogs
     */
    @GetMapping("/with-both-cat-and-dog")
    suspend fun getOwnersWithBothCatAndDog(): ResponseEntity<Flux<Owner?>> {
        val owners = ownerService.findOwnersWithBothCatAndDog()
        return ResponseEntity.ok(owners)
    }
    
    /**
     * Get names of owners who have both cats and dogs
     * 
     * @return a list of owner full names
     */
    @GetMapping("/with-both-cat-and-dog/names")
    suspend fun getOwnerNamesWithBothCatAndDog(): ResponseEntity<Flux<String?>> {
        val ownerNames = ownerService.findOwnersWithBothCatAndDog().map { it?.getFullName() }
        return ResponseEntity.ok(ownerNames)
    }
}
