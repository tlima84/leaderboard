package com.tlima84.leaderboards.score.controllers.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.Min
import jakarta.validation.constraints.NotBlank
import lombok.AllArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
@Schema(description = "Model of a score that will be part of the leaderboards")
data class ScoreDTO(
    @field:Schema(
        description = "Score unique identification number",
        example = "1",
        type = "Long"
    )
    var id: Long?,
    @field:Schema(
        description = "Name of the person that score the points",
        example = "Paul",
        type = "String",
        required = true
    )
    @field:NotBlank(message = "Score can not be insert without a name")
    var name: String,
    @field:Min(value = 0, message = "field value cannot be negative")
    @field:Schema(
        description = "Scored points",
        example = "150",
        type = "int",
        minimum = "0",
        required = true
    )
    var value: Int,

    @field:Schema(
        description = "Data when the score was inserted.",
        example = "2023-03-27T21:59:14.712418",
        type = "LocalDateTime"
    )
    var createDate: LocalDateTime?
)
