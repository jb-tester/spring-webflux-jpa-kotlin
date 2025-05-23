package org.example.demoktmv.repository

import org.example.demoktmv.model.Cat
import org.springframework.data.repository.kotlin.CoroutineCrudRepository
import org.springframework.stereotype.Repository
import reactor.core.publisher.Flux

@Repository
interface CatRepository : CoroutineCrudRepository<Cat, Long> {
    fun findByMeowVolumeAfter(meowVolume: Int): Flux<Cat?>
}
