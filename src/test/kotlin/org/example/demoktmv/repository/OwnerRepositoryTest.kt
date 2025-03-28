package org.example.demoktmv.repository

import org.example.demoktmv.model.Cat
import org.example.demoktmv.model.Dog
import org.example.demoktmv.model.Owner
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager
import org.springframework.test.context.TestPropertySource
import kotlin.test.assertEquals
import kotlin.test.assertTrue



@DataJpaTest
@TestPropertySource(properties = [
    "spring.sql.init.mode=never",
    "spring.jpa.hibernate.ddl-auto=create-drop"
])
class OwnerRepositoryTest {

    @Autowired
    private lateinit var entityManager: TestEntityManager

    @Autowired
    private lateinit var ownerRepository: OwnerRepository

    @Test
    fun `should find animal names by owner id`() {
        // Given
        val owner = Owner(firstName = "John", lastName = "Doe")
        entityManager.persist(owner)
        
        val cat = Cat(name = "Whiskers", age = 3, owner = owner, meowVolume = 7)
        val dog = Dog(name = "Max", age = 5, owner = owner, barkVolume = 9)
        
        owner.addAnimal(cat)
        owner.addAnimal(dog)
        
        entityManager.persist(cat)
        entityManager.persist(dog)
        entityManager.flush()
        
        // When
        val animalNames = ownerRepository.findAnimalNamesByOwnerId(owner.id)
        
        // Then
        assertEquals(2, animalNames.size)
        assertTrue(animalNames.contains("Whiskers"))
        assertTrue(animalNames.contains("Max"))
    }

    @Test
    fun `should return empty list when owner has no animals`() {
        // Given
        val owner = Owner(firstName = "Jane", lastName = "Smith")
        entityManager.persist(owner)
        entityManager.flush()
        
        // When
        val animalNames = ownerRepository.findAnimalNamesByOwnerId(owner.id)
        
        // Then
        assertTrue(animalNames.isEmpty())
    }

    @Test
    fun `should find animal names by owner first name and last name`() {
        // Given
        val owner = Owner(firstName = "Jane", lastName = "Smith")
        entityManager.persist(owner)
        
        val cat = Cat(name = "Mittens", age = 2, owner = owner, meowVolume = 5)
        val dog = Dog(name = "Buddy", age = 3, owner = owner, barkVolume = 8)
        
        owner.addAnimal(cat)
        owner.addAnimal(dog)
        
        entityManager.persist(cat)
        entityManager.persist(dog)
        entityManager.flush()
        
        // When
        val animalNames = ownerRepository.findAnimalNamesByOwnerName("Jane", "Smith")
        
        // Then
        assertEquals(2, animalNames.size)
        assertTrue(animalNames.contains("Mittens"))
        assertTrue(animalNames.contains("Buddy"))
    }

    @Test
    fun `should return empty list when no owner matches name criteria`() {
        // When
        val animalNames = ownerRepository.findAnimalNamesByOwnerName("Nonexistent", "Owner")
        
        // Then
        assertTrue(animalNames.isEmpty())
    }
    
    @Test
    fun `should find owners with both cat and dog`() {
        // Given
        val owner1 = Owner(firstName = "John", lastName = "Doe")
        entityManager.persist(owner1)
        
        val cat1 = Cat(name = "Whiskers", age = 3, owner = owner1, meowVolume = 7)
        val dog1 = Dog(name = "Max", age = 5, owner = owner1, barkVolume = 9)
        
        owner1.addAnimal(cat1)
        owner1.addAnimal(dog1)
        
        entityManager.persist(cat1)
        entityManager.persist(dog1)
        
        // Owner with only cat
        val owner2 = Owner(firstName = "Alice", lastName = "Johnson")
        entityManager.persist(owner2)
        
        val cat2 = Cat(name = "Tiger", age = 2, owner = owner2, meowVolume = 6)
        owner2.addAnimal(cat2)
        entityManager.persist(cat2)
        
        // Owner with only dog
        val owner3 = Owner(firstName = "Bob", lastName = "Brown")
        entityManager.persist(owner3)
        
        val dog3 = Dog(name = "Rex", age = 4, owner = owner3, barkVolume = 8)
        owner3.addAnimal(dog3)
        entityManager.persist(dog3)
        
        entityManager.flush()
        
        // When
        val ownersWithBoth = ownerRepository.findOwnersWithBothCatAndDog()
        
        // Then
        assertEquals(1, ownersWithBoth.size)
        assertEquals("John", ownersWithBoth[0].firstName)
        assertEquals("Doe", ownersWithBoth[0].lastName)
    }
    
    @Test
    fun `should return empty list when no owners have both cat and dog`() {
        // Given
        // Owner with only cat
        val owner1 = Owner(firstName = "Alice", lastName = "Johnson")
        entityManager.persist(owner1)
        
        val cat1 = Cat(name = "Tiger", age = 2, owner = owner1, meowVolume = 6)
        owner1.addAnimal(cat1)
        entityManager.persist(cat1)
        
        // Owner with only dog
        val owner2 = Owner(firstName = "Bob", lastName = "Brown")
        entityManager.persist(owner2)
        
        val dog2 = Dog(name = "Rex", age = 4, owner = owner2, barkVolume = 8)
        owner2.addAnimal(dog2)
        entityManager.persist(dog2)
        
        entityManager.flush()
        
        // When
        val ownersWithBoth = ownerRepository.findOwnersWithBothCatAndDog()
        
        // Then
        assertTrue(ownersWithBoth.isEmpty())
    }
}
