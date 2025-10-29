package org.mytests.spring.controller

import org.mytests.spring.model.Owner
import org.mytests.spring.service.OwnerService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

/**
 * This controller is deprecated:
 * Use the functional endpoints instead.
 */
@Deprecated("Use functional endpoints with OwnerHandler instead")
@RestController
@RequestMapping("/api/deprecated/owners")
class OwnerController(private val ownerService: OwnerService) {
    

    @GetMapping("/{id}/animal-names")
    fun getAnimalNamesByOwnerId(@PathVariable id: Long): ResponseEntity<List<String>> {
        val animalNames = ownerService.getAnimalNamesByOwnerId(id)
        return ResponseEntity.ok(animalNames)
    }
    

    @GetMapping("/animal-names")
    fun getAnimalNamesByOwnerName(
        @RequestParam firstName: String,
        @RequestParam lastName: String
    ): ResponseEntity<List<String>> {
        val animalNames = ownerService.getAnimalNamesByOwnerName(firstName, lastName)
        return ResponseEntity.ok(animalNames)
    }
    

    @GetMapping("/by{id}")
    fun getOwnerById(@PathVariable id: Long): ResponseEntity<Owner> {
        val owner = ownerService.getOwnerById(id)
        return if (owner != null) {
            ResponseEntity.ok(owner)
        } else {
            ResponseEntity.notFound().build()
        }
    }
    

    @GetMapping("/with-both-cat-and-dog")
    fun getOwnersWithBothCatAndDog(): ResponseEntity<List<Owner>> {
        val owners = ownerService.findOwnersWithBothCatAndDog()
        return ResponseEntity.ok(owners)
    }
    

    @GetMapping("/with-both-cat-and-dog/names")
    fun getOwnerNamesWithBothCatAndDog(): ResponseEntity<List<String>> {
        val ownerNames = ownerService.findOwnersWithBothCatAndDog().map { it.getFullName() }
        return ResponseEntity.ok(ownerNames)
    }
}
