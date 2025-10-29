package org.mytests.spring.repository

import org.mytests.spring.model.Owner
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import org.springframework.stereotype.Repository

@Repository
interface OwnerRepository : JpaRepository<Owner, Long> {
    

    @Query("SELECT a.name FROM Animal a WHERE a.owner.id = :ownerId")
    fun findAnimalNamesByOwnerId(@Param("ownerId") ownerId: Long?): List<String>
    

    @Query("SELECT a.name FROM Animal a WHERE a.owner.firstName = :firstName AND a.owner.lastName = :lastName")
    fun findAnimalNamesByOwnerName(
        @Param("firstName") firstName: String, 
        @Param("lastName") lastName: String
    ): List<String>
    

    @Query(
        """
        SELECT o FROM Owner o
        WHERE EXISTS (
            SELECT 1 FROM Animal a 
            WHERE a.owner = o AND a.class = org.mytests.spring.model.Cat
        ) 
        AND EXISTS (
            SELECT 1 FROM Animal a 
            WHERE a.owner = o AND a.class = org.mytests.spring.model.Dog
        )
    """
    )
    fun findOwnersWithBothCatAndDog(): List<Owner>
    

}
