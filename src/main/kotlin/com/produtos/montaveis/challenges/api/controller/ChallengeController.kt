package com.produtos.montaveis.challenges.api.controller

import com.produtos.montaveis.challenges.domain.model.Challenge
import com.produtos.montaveis.challenges.domain.model.ChallengeKey
import com.produtos.montaveis.challenges.domain.repository.ChallengeRepository
import com.produtos.montaveis.challenges.domain.repository.FormulaRepository
import com.produtos.montaveis.challenges.domain.repository.StudentRepository
import com.produtos.montaveis.challenges.domain.service.ChallengeService
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.time.OffsetDateTime

@RestController
@RequestMapping("/students/{studentId}/challenges")
class ChallengeController(
    val challengeRepository: ChallengeRepository,
    val studentRepository: StudentRepository,
    val formulaRepository: FormulaRepository,
    val challengeService: ChallengeService
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

    @PutMapping("/{formulaId}/start")
    fun startChallenge(
        @PathVariable studentId: Long,
        @PathVariable formulaId: Int
    ): ResponseEntity<Challenge> {
        return if (!studentRepository.existsById(studentId) || !formulaRepository.existsById(formulaId))
            ResponseEntity.notFound().build()
        else {
            val updatedChallenge = challengeRepository
                .findByIdOrNull(ChallengeKey(studentId, formulaId))?.copy(
                    startDateTime = OffsetDateTime.now())!!
            ResponseEntity.ok(challengeRepository.save(updatedChallenge))
        }
    }

    @PutMapping("/{formulaId}/update-status")
    fun updateProgress(
        @PathVariable studentId: Long,
        @PathVariable formulaId: Int,
        @RequestBody progress: Double
    ): ResponseEntity<Challenge> {
        return if (!studentRepository.existsById(studentId) || !formulaRepository.existsById(formulaId))
            ResponseEntity.notFound().build()
        else {
            val updatedChallenge = challengeRepository
                .findByIdOrNull(ChallengeKey(studentId, formulaId))?.copy(
                    progressStatus = progress
                )!!
            ResponseEntity.ok(challengeRepository.save(updatedChallenge))
        }
    }

    @PutMapping("/{formulaId}/finish")
    fun endChallenge(
        @PathVariable studentId: Long,
        @PathVariable formulaId: Int
    ): ResponseEntity<Challenge> {
        return if (!studentRepository.existsById(studentId) || !formulaRepository.existsById(formulaId))
            ResponseEntity.notFound().build()
        else {
            val updatedChallenge = challengeRepository
                .findByIdOrNull(ChallengeKey(studentId, formulaId))?.copy(
                    finishDateTime = OffsetDateTime.now(),
                    progressStatus = 100.0
                )!!
            val student = studentRepository.findById(studentId).orElseThrow()
                .let {
                    it.copy(level = it.level + 1, score = updatedChallenge.formula.id * 100)
                }
            studentRepository.save(student)
            ResponseEntity.ok(challengeRepository.save(updatedChallenge))
        }
    }
}