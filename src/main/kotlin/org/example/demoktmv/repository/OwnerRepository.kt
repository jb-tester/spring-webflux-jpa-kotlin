package org.example.demoktmv.repository

import org.example.demoktmv.model.Owner
import org.springframework.data.r2dbc.repository.Query
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface OwnerRepository : CoroutineCrudRepository<Owner, Long> {
    
    /**
     * Find all animal names belonging to an owner with the given ID
     * 
     * @param ownerId the ID of the owner
     * @return a list of animal names
     */
    @Query("SELECT a.name FROM Animal a WHERE a.owner.id = :ownerId")
    suspend fun findAnimalNamesByOwnerId(@Param("ownerId") ownerId: Long): Flux<String>
    
    /**
     * Find all animal names belonging to an owner with the given first name and last name
     * 
     * @param firstName the first name of the owner
     * @param lastName the last name of the owner
     * @return a list of animal names
     */
    @Query("SELECT a.name FROM Animal a WHERE a.owner.firstName = :firstName AND a.owner.lastName = :lastName")
    suspend fun findAnimalNamesByOwnerName(
        @Param("firstName") firstName: String, 
        @Param("lastName") lastName: String
    ): Flux<String>
    
    /**
     * Find all owners who have both cats and dogs
     * 
     * @return a list of owners who have both cats and dogs
     */
    @Query("""
        SELECT o FROM Owner o
        WHERE EXISTS (
            SELECT 1 FROM Animal a 
            WHERE a.owner = o AND a.class = org.example.demoktmv.model.Cat
        ) 
        AND EXISTS (
            SELECT 1 FROM Animal a 
            WHERE a.owner = o AND a.class = org.example.demoktmv.model.Dog
        )
    """)
    suspend fun findOwnersWithBothCatAndDog(): Flux<Owner?>
    

}
