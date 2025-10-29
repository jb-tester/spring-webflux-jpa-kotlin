package org.mytests.spring.repository

import org.mytests.spring.model.Animal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : JpaRepository<Animal, Long> {
    fun findByName(name: String): List<Animal?>
}
