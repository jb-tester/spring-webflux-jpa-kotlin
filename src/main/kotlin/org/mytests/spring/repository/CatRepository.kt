package org.mytests.spring.repository

import org.mytests.spring.model.Cat
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface CatRepository : JpaRepository<Cat, Long> {
    fun findByMeowVolumeAfter(meowVolume: Int): List<Cat?>
}
