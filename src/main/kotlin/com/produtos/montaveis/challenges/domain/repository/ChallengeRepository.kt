package com.produtos.montaveis.challenges.domain.repository

import com.produtos.montaveis.challenges.domain.model.Challenge
import com.produtos.montaveis.challenges.domain.model.ChallengeKey
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository

@Repository
interface ChallengeRepository : JpaRepository<Challenge, ChallengeKey> {

    @Query("SELECT * FROM tb_challenges WHERE student_id = ?1", nativeQuery = true)
    fun findAllByStudentId(studentId: Long): List<Challenge>?
}