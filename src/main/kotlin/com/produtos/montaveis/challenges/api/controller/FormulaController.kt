package com.produtos.montaveis.challenges.api.controller

import com.produtos.montaveis.challenges.domain.model.Formula
import com.produtos.montaveis.challenges.domain.repository.FormulaRepository
import lombok.AllArgsConstructor
import org.springframework.data.repository.findByIdOrNull
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/formulas")
class FormulaController(val formulaRepository: FormulaRepository) {

    @GetMapping
    fun listFormulas(): List<Formula> {
        return formulaRepository.findAll()
    }

    @GetMapping("/{formulaId}")
    fun getFormula(@PathVariable formulaId: Int): ResponseEntity<Formula> {
        return if (formulaRepository.existsById(formulaId)) {
            ResponseEntity.ok(formulaRepository.findByIdOrNull(formulaId))
        } else ResponseEntity.notFound().build()
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    fun addFormula(@RequestBody formula: Formula): ResponseEntity<Formula> {
        return ResponseEntity.ok(formulaRepository.save(formula))
    }

    @PutMapping("/{formulaId}")
    fun updateFormula(@PathVariable formulaId: Int, @RequestBody formula: Formula): ResponseEntity<Formula> {
        return if (formulaRepository.existsById(formulaId)) {
            val updatedFormula = formula.copy(id = formulaId)
            ResponseEntity.ok(formulaRepository.save(updatedFormula))
        } else ResponseEntity.notFound().build()
    }

    @DeleteMapping("/{formulaId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteFormula(@PathVariable formulaId: Int): ResponseEntity<Formula> {
        return if (formulaRepository.existsById(formulaId)) {
            formulaRepository.deleteById(formulaId)
            ResponseEntity.noContent().build()
        } else ResponseEntity.notFound().build()
    }

}