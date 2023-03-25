package com.tlima84.leaderboards.score.controllers.dto

import jakarta.validation.constraints.Size
import lombok.AllArgsConstructor
import java.time.LocalDateTime

@AllArgsConstructor
data class ScoreDTO(
    var id: Long?,
    @field:Size(min = 3, max = 3, message = "The input string must have exactly 3 characters")
    var name: String,
    var value: Long,
    var createDate: LocalDateTime?
)
