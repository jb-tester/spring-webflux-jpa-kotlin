package org.example.demoktmv.repository

import org.example.demoktmv.model.Cat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CatRepository : JpaRepository<Cat, Long> {
    fun findByMeowVolumeAfter(meowVolume: Int): List<Cat?>
}
