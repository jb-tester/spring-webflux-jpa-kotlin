package org.mytests.spring.service

import org.mytests.spring.model.Cat
import org.mytests.spring.repository.CatRepository
import org.springframework.stereotype.Service

@Service
class CatService(private val catRepository: CatRepository) {
    

    fun findAll(): List<Cat> {
        return catRepository.findAll()
    }

    fun bySound(): List<Cat?> {
        return catRepository.findByMeowVolumeAfter(5)
    }

}


