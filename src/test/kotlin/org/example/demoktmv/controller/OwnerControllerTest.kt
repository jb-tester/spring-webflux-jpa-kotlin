package org.example.demoktmv.controller

import com.fasterxml.jackson.databind.ObjectMapper
import org.example.demoktmv.model.Owner
import org.example.demoktmv.service.OwnerService
import org.junit.jupiter.api.Test
import org.mockito.kotlin.whenever
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest
import org.springframework.http.MediaType
import org.springframework.test.context.bean.override.mockito.MockitoBean
import org.springframework.test.web.reactive.server.WebTestClient

@WebFluxTest(OwnerController::class)
class OwnerControllerTest {

    @Autowired
    private lateinit var webTestClient: WebTestClient

    @MockitoBean
    private lateinit var ownerService: OwnerService

   // @Autowired
  //  private lateinit var objectMapper: ObjectMapper

    @Test
    fun `should return animal names by owner id`() {
        val ownerId = 1L
        val expectedAnimalNames = listOf("Rex", "Fluffy")

        whenever(ownerService.getAnimalNamesByOwnerId(ownerId)).thenReturn(expectedAnimalNames)

        webTestClient.get()
            .uri("/api/deprecated/owners/$ownerId/animal-names")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$[0]").isEqualTo("Rex")
            .jsonPath("$[1]").isEqualTo("Fluffy")
    }

    @Test
    fun `should return animal names by owner name`() {
        val firstName = "John"
        val lastName = "Doe"
        val expectedAnimalNames = listOf("Rex", "Fluffy")

        whenever(ownerService.getAnimalNamesByOwnerName(firstName, lastName)).thenReturn(expectedAnimalNames)

        webTestClient.get()
            .uri { builder -> 
                builder.path("/api/deprecated/owners/animal-names")
                    .queryParam("firstName", firstName)
                    .queryParam("lastName", lastName)
                    .build() 
            }
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$[0]").isEqualTo("Rex")
            .jsonPath("$[1]").isEqualTo("Fluffy")
    }

    @Test
    fun `should return owner by id when owner exists`() {
        val ownerId = 1L
        val owner = Owner(id = ownerId, firstName = "John", lastName = "Doe")

        whenever(ownerService.getOwnerById(ownerId)).thenReturn(owner)

        webTestClient.get()
            .uri("/api/deprecated/owners/by$ownerId")
            .accept(MediaType.APPLICATION_JSON)
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

        whenever(ownerService.getOwnerById(ownerId)).thenReturn(null)

        webTestClient.get()
            .uri("/api/deprecated/owners/$ownerId")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isNotFound
    }

    @Test
    fun `should return owners with both cat and dog`() {
        val owner1 = Owner(id = 1L, firstName = "John", lastName = "Doe")
        val owner2 = Owner(id = 2L, firstName = "Jane", lastName = "Smith")
        val expectedOwners = listOf(owner1, owner2)

        whenever(ownerService.findOwnersWithBothCatAndDog()).thenReturn(expectedOwners)

        webTestClient.get()
            .uri("/api/deprecated/owners/with-both-cat-and-dog")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
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
        val expectedNames = listOf("John Doe", "Jane Smith")

        whenever(ownerService.findOwnersWithBothCatAndDog()).thenReturn(expectedOwners)

        webTestClient.get()
            .uri("/api/deprecated/owners/with-both-cat-and-dog/names")
            .accept(MediaType.APPLICATION_JSON)
            .exchange()
            .expectStatus().isOk
            .expectBody()
            .jsonPath("$").isArray
            .jsonPath("$.length()").isEqualTo(2)
            .jsonPath("$[0]").isEqualTo("John Doe")
            .jsonPath("$[1]").isEqualTo("Jane Smith")
    }
}
