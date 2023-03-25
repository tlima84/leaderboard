package com.tlima84.leaderboards.score.controllers.dto

import com.fasterxml.jackson.annotation.JsonProperty
import lombok.AllArgsConstructor
import java.io.Serializable

@AllArgsConstructor
data class LeaderboardDTO(@JsonProperty("id")val id:Long, @JsonProperty("name") val name: String,@JsonProperty("value") val value:Long):
    Serializable