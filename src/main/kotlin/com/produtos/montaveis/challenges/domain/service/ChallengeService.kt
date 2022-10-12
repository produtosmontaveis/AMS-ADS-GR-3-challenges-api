package com.produtos.montaveis.challenges.domain.service

import com.produtos.montaveis.challenges.domain.model.Challenge
import com.produtos.montaveis.challenges.domain.model.ChallengeKey
import com.produtos.montaveis.challenges.domain.model.Student
import com.produtos.montaveis.challenges.domain.repository.ChallengeRepository
import com.produtos.montaveis.challenges.domain.repository.FormulaRepository
import org.springframework.stereotype.Service

@Service
class ChallengeService(
    val formulaRepository: FormulaRepository,
    val challengeRepository: ChallengeRepository
    ) {
    fun createChallenges(student: Student) {
        val challenges = mutableListOf<Challenge>()
        formulaRepository.findAll().forEach { formula ->
            challenges.add(
                Challenge(
                    ChallengeKey(student.id, formula.id),
                    student, formula
                )
            )
        }
        challengeRepository.saveAll(challenges)
    }
}