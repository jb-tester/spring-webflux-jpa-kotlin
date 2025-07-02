package org.example.demoktmv.model

import jakarta.persistence.*

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "animal_type")
abstract class Animal(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0,

    val name: String,
    
    val age: Int,

    @Version
    var version: Long = 0,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "owner_id")
    var owner: Owner? = null
)
