package com.produtos.montaveis.challenges.domain.repository

import com.produtos.montaveis.challenges.domain.model.Student
import org.springframework.data.jpa.repository.JpaRepository

interface StudentRepository : JpaRepository<Student, Long> {
}