package com.produtos.montaveis.challenges.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:NotBlank @Size(max = 50) val name: String,

    val level: Int = 0,

    val score: Int = 0,

    @JsonIgnore
    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val challenges: List<Challenge>? = listOf()
)
