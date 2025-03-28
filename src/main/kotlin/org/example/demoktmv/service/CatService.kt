package org.example.demoktmv.service

import org.example.demoktmv.model.Cat
import org.example.demoktmv.repository.CatRepository
import org.springframework.orm.ObjectOptimisticLockingFailureException
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.Optional

@Service
class CatService(private val catRepository: CatRepository) {
    
    /**
     * Find all cats
     */
    fun findAll(): List<Cat> {
        return catRepository.findAll()
    }

    fun bySound(): List<Cat?> {
        return catRepository.findByMeowVolumeAfter(5)
    }

}


