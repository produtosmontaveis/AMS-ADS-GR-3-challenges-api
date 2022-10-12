package com.produtos.montaveis.challenges.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "tb_students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotBlank val name: String,

    val level: Int = 0,

    @Column(name = "exercises_completed")
    val exercisesCompleted: Int = 0
)
