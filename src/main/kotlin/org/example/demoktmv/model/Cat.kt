package org.example.demoktmv.model

import jakarta.persistence.DiscriminatorValue
import jakarta.persistence.Entity

@Entity
@DiscriminatorValue("CAT")
class Cat(
    id: Long = 0,
    name: String,
    age: Int,
    owner: Owner? = null,
    version: Long = 0,
    val meowVolume: Int
) : Animal(id, name, age, version, owner)
