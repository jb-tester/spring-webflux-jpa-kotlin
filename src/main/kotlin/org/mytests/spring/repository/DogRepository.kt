package org.mytests.spring.repository

import org.mytests.spring.model.Dog
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface DogRepository : JpaRepository<Dog, Long>
