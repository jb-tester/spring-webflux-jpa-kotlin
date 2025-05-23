package org.example.demoktmv.service

import kotlinx.coroutines.flow.Flow
import org.example.demoktmv.model.Cat
import org.example.demoktmv.repository.CatRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import reactor.core.publisher.Flux
import java.util.Optional

@Service
class CatService(private val catRepository: CatRepository) {
    
    /**
     * Find all cats
     */
    fun findAll(): Flow<Cat> {
        return catRepository.findAll()
    }

    fun bySound(): Flux<Cat?> {
        return catRepository.findByMeowVolumeAfter(5)
    }

}


