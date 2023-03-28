package com.tlima84.leaderboards.score.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import io.swagger.v3.oas.annotations.media.Schema
import lombok.AllArgsConstructor
import java.io.Serializable

@AllArgsConstructor
data class LeaderboardDTO(

    @field:Schema(
        description = "Score unique identification number",
        example = "1",
        type = "Long"
    )
    @JsonProperty("id")
    val id:Long,

    @field:Schema(
        description = "Name of the person that score the points",
        example = "Paul",
        type = "String",
        required = true
    )
    @JsonProperty("name")
    val name: String,

    @field:Schema(
        description = "Scored points",
        example = "150",
        type = "int",
        minimum = "0",
        required = true
    )
    @JsonProperty("value")
    val value:Int
    ): Serializable