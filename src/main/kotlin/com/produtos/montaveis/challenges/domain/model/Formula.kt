package com.produtos.montaveis.challenges.domain.model

import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.validation.constraints.NotBlank

@Entity(name = "tb_formulas")
data class Formula(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @NotBlank val name: String,

    @Column(name = "img_url")
    @NotBlank val imgUrl: String

)
