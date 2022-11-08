package com.produtos.montaveis.challenges.domain.model

import javax.persistence.*
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "tb_students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotBlank @Size(max = 50) val name: String,

    val level: Int = 0,

    @Column(name = "score")
    val score: Int = 0,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val challenges: List<Challenge>? = listOf()
)
