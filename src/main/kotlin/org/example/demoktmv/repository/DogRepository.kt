package org.example.demoktmv.repository

import org.example.demoktmv.model.Dog
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository : CoroutineCrudRepository<Dog, Long>
