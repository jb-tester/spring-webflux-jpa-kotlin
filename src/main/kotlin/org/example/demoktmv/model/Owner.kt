package org.example.demoktmv.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.persistence.*

@Entity
class Owner(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,
    
    val firstName: String,
    
    val lastName: String,

    @JsonIgnore
    @OneToMany(fetch = FetchType.LAZY,
            mappedBy = "owner", cascade = [CascadeType.ALL], orphanRemoval = true)
    val animals: MutableList<Animal> = mutableListOf()
) {
    // Helper method to add an animal to the owner
    fun addAnimal(animal: Animal) {
        animals.add(animal)
        animal.owner = this
    }
    
    // Helper method to remove an animal from the owner
    fun removeAnimal(animal: Animal) {
        animals.remove(animal)
        animal.owner = null
    }
    
    /**
     * Check if this owner has both cat and dog
     * 
     * @return true if the owner has at least one cat and at least one dog, false otherwise
     */
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
    
    /**
     * Get full name of the owner
     * 
     * @return the full name (first name + last name)
     */
    fun getFullName(): String {
        return "$firstName $lastName"
    }
}
