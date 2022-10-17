package com.produtos.montaveis.challenges.domain.model

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonProperty
import lombok.EqualsAndHashCode
import javax.persistence.Entity
import java.io.Serializable
import java.time.OffsetDateTime
import javax.persistence.Column
import javax.persistence.Embeddable
import javax.persistence.EmbeddedId
import javax.persistence.JoinColumn
import javax.persistence.ManyToOne
import javax.persistence.MapsId

@Entity(name = "tb_challenges")
data class Challenge(
    @JsonIgnore
    @EmbeddedId
    val id: ChallengeKey,

    @JsonIgnore
    @ManyToOne
    @MapsId("studentId")
    @JoinColumn(name = "student_id")
    val student: Student,

    @ManyToOne
    @MapsId("formulaId")
    @JoinColumn(name = "formula_id")
    val formula: Formula,

    @Column(name = "progress_status")
    val progressStatus: Double = 0.0,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "start_date_time")
    val startDateTime: OffsetDateTime? = null,

    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "end_date_time")
    val endDateTime: OffsetDateTime? = null
)

@Embeddable
@EqualsAndHashCode
class ChallengeKey(
    @Column(name = "student_id") val studentId: Long,

    @Column(name = "formula_id") val formulaId: Int
) : Serializable
