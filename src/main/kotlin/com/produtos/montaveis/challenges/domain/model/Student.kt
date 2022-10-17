package com.produtos.montaveis.challenges.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.CascadeType
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "tb_students")
data class Student(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @NotBlank @Size(max = 50) val name: String,

    val level: Int = 0,

    @Column(name = "exercises_completed")
    val exercisesCompleted: Int = 0,

    @OneToMany(mappedBy = "student", cascade = [CascadeType.ALL])
    val challenges: List<Challenge>? = listOf()
)
