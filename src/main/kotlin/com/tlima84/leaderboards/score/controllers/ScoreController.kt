package com.tlima84.leaderboards.score.controllers

import com.tlima84.leaderboards.score.controllers.dto.CreateScoreDTO
import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO
import com.tlima84.leaderboards.score.services.ScoreService
import io.swagger.v3.oas.annotations.Operation
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*
import java.time.Month

@RestController
@RequestMapping("/v1/scores" , produces = [MediaType.APPLICATION_JSON_VALUE])
class ScoreController(private val scoreService: ScoreService) {
    @GetMapping("all-time")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves all time leaderboard", description = "Retrieves all time leaderboard ordered by score value DESC and createdDate ASC")
    fun getAllTimeLeaderBoard() : List<LeaderboardDTO> {
        return scoreService.getAllTimeScores()
    }

    @GetMapping("monthly")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Retrieves monthly leaderboard", description = "Retrieves monthly leaderboard ordered by score value DESC and createdDate ASC")
    fun getMonthlyLeaderBoard(month: Month) : List<LeaderboardDTO> {
        return scoreService.getScoreByMonth(month)
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a new score", description = "Adds a new score into the system, it must contain value and the name of the user.")
    fun saveScore(@Valid @RequestBody scoreDTO: CreateScoreDTO): ScoreDTO{
        return scoreService.addScore(scoreDTO)
    }
}