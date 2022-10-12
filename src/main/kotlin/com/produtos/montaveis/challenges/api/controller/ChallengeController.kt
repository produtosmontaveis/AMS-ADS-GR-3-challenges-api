package com.produtos.montaveis.challenges.api.controller

import com.produtos.montaveis.challenges.domain.model.Challenge
import com.produtos.montaveis.challenges.domain.model.ChallengeKey
import com.produtos.montaveis.challenges.domain.repository.ChallengeRepository
import com.produtos.montaveis.challenges.domain.repository.FormulaRepository
import com.produtos.montaveis.challenges.domain.repository.StudentRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime

@RestController
@RequestMapping("/students/{studentId}/challenges")
class ChallengeController(
    val challengeRepository: ChallengeRepository,
    val studentRepository: StudentRepository,
    val formulaRepository: FormulaRepository
) {

    @GetMapping
    fun listChallenges(@PathVariable studentId: Long): List<Challenge> {
        return challengeRepository.findAllByStudentId(studentId).orEmpty()
    }

    @GetMapping("/{formulaId}")
    fun getChallenge(@PathVariable studentId: Long, @PathVariable formulaId: Int): ResponseEntity<Challenge> {
        return if (!studentRepository.existsById(studentId) || !formulaRepository.existsById(formulaId))
            ResponseEntity.notFound().build()
        else ResponseEntity.ok(
            challengeRepository.findByIdOrNull(ChallengeKey(studentId, formulaId))
        )
    }
}