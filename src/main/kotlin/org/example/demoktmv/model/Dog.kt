package org.example.demoktmv.model

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("DOG")
class Dog(
    id: Long = 0,
    name: String,
    age: Int,
    owner: Owner? = null,
    version: Long = 0,
    val barkVolume: Int
) : Animal(id, name, age, version, owner)
