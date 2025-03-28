package org.example.demoktmv.repository

import org.example.demoktmv.model.Dog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository : JpaRepository<Dog, Long>
