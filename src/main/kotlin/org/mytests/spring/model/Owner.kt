package org.mytests.spring.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,
    
    val firstName: String,
    
    val lastName: String,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val animals: MutableList<Animal> = mutableListOf()
) {
    fun addAnimal(animal: Animal) {
        animals.add(animal)
        animal.owner = this
    }
    
    fun removeAnimal(animal: Animal) {
        animals.remove(animal)
        animal.owner = null
    }
    

    fun hasBothCatAndDog(): Boolean {
        var hasCat = false
        var hasDog = false
        
        for (animal in animals) {
            when (animal) {
                is Cat -> hasCat = true
                is Dog -> hasDog = true
            }
            
            if (hasCat && hasDog) {
                return true
            }
        }
        
        return false
    }
    

    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
