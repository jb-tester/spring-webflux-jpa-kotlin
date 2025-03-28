package org.example.demoktmv.repository

import org.example.demoktmv.model.Animal
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface AnimalRepository : JpaRepository<Animal, Long> {
    fun findByName(name: String): List<Animal?>
}
