package com.produtos.montaveis.challenges.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import javax.persistence.Column
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import javax.persistence.OneToMany
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

@Entity(name = "tb_formulas")
data class Formula(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Int,

    @NotBlank @Size(max = 30) val name: String,

    @Column(name = "img_url")
    @NotBlank val imgUrl: String,

    @JsonIgnore
    @OneToMany(mappedBy = "formula")
    val challenges: List<Challenge>? = listOf(),

    val badgeImageUrl: String
)
