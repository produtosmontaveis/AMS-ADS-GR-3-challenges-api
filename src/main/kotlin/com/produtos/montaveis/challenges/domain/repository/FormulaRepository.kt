package com.produtos.montaveis.challenges.domain.repository

import com.produtos.montaveis.challenges.domain.model.Formula
import org.springframework.data.jpa.repository.JpaRepository

interface FormulaRepository : JpaRepository<Formula, Int> {
}