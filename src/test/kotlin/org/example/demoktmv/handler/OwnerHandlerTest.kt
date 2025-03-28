package org.example.demoktmv.handler

import org.example.demoktmv.model.Owner
import org.example.demoktmv.service.OwnerService
import org.junit.jupiter.api.Test
import org.mockito.Mockito.`when`
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.reactive.server.WebTestClient
import org.example.demoktmv.controller.OwnerRouterConfig
import org.example.demoktmv.service.OwnerHandler
import org.springframework.test.context.bean.override.mockito.MockitoBean

@WebFluxTest
@Import(OwnerRouterConfig::class, OwnerHandler::class)
class OwnerHandlerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockitoBean
    private lateinit var ownerService: OwnerService

    @Test
    fun `should return animal names by owner id`() {
        val ownerId = 1L
        val expectedAnimalNames = listOf("Rex", "Fluffy")

        `when`(ownerService.getAnimalNamesByOwnerId(ownerId)).thenReturn(expectedAnimalNames)

        webTestClient.get()
            .uri("/api/owners/$ownerId/animal-names")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$[0]").isEqualTo("Rex")
            .jsonPath("$[1]").isEqualTo("Fluffy")
            .jsonPath("$.length()").isEqualTo(2)
    }

    @Test
    fun `should return animal names by owner name`() {
        val firstName = "John"
        val lastName = "Doe"
        val expectedAnimalNames = listOf("Rex", "Fluffy")

        `when`(ownerService.getAnimalNamesByOwnerName(firstName, lastName)).thenReturn(expectedAnimalNames)

        webTestClient.get()
            .uri { builder -> 
                builder.path("/api/owners/animal-names")
                    .queryParam("firstName", firstName)
                    .queryParam("lastName", lastName)
                    .build() 
            }
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$[0]").isEqualTo("Rex")
            .jsonPath("$[1]").isEqualTo("Fluffy")
            .jsonPath("$.length()").isEqualTo(2)
    }

    @Test
    fun `should return owner by id when owner exists`() {
        val ownerId = 1L
        val owner = Owner(id = ownerId, firstName = "John", lastName = "Doe")

        `when`(ownerService.getOwnerById(ownerId)).thenReturn(owner)

        webTestClient.get()
            .uri("/api/owners/by$ownerId")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$.id").isEqualTo(ownerId)
            .jsonPath("$.firstName").isEqualTo("John")
            .jsonPath("$.lastName").isEqualTo("Doe")
    }

    @Test
    fun `should return 404 when owner does not exist`() {
        val ownerId = 999L

        `when`(ownerService.getOwnerById(ownerId)).thenReturn(null)

        webTestClient.get()
            .uri("/api/owners/by$ownerId")
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun `should return owners with both cat and dog`() {
        val owner1 = Owner(id = 1L, firstName = "John", lastName = "Doe")
        val owner2 = Owner(id = 2L, firstName = "Jane", lastName = "Smith")
        val expectedOwners = listOf(owner1, owner2)

        `when`(ownerService.findOwnersWithBothCatAndDog()).thenReturn(expectedOwners)

        webTestClient.get()
            .uri("/api/owners/with-both-cat-and-dog")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$[0].id").isEqualTo(1)
            .jsonPath("$[0].firstName").isEqualTo("John")
            .jsonPath("$[0].lastName").isEqualTo("Doe")
            .jsonPath("$[1].id").isEqualTo(2)
            .jsonPath("$[1].firstName").isEqualTo("Jane")
            .jsonPath("$[1].lastName").isEqualTo("Smith")
    }

    @Test
    fun `should return names of owners with both cat and dog`() {
        val owner1 = Owner(id = 1L, firstName = "John", lastName = "Doe")
        val owner2 = Owner(id = 2L, firstName = "Jane", lastName = "Smith")
        val expectedOwners = listOf(owner1, owner2)

        `when`(ownerService.findOwnersWithBothCatAndDog()).thenReturn(expectedOwners)

        webTestClient.get()
            .uri("/api/owners/with-both-cat-and-dog/names")
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray()
            .jsonPath("$[0]").isEqualTo("John Doe")
            .jsonPath("$[1]").isEqualTo("Jane Smith")
            .jsonPath("$.length()").isEqualTo(2)
    }
}
