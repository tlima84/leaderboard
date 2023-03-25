package com.tlima84.leaderboards.score.controllers

import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO
import com.tlima84.leaderboards.score.services.ScoreService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/score")
class ScoreController(private val scoreService: ScoreService) {
    @GetMapping("all-time")
    @ResponseStatus(HttpStatus.OK)
    fun getAllTimeLeaderBoard() : List<LeaderboardDTO> {
        return scoreService.getAlltimeScores()
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun saveScore(@Valid @RequestBody scoreDTO: ScoreDTO): ScoreDTO{
        return scoreService.addScore(scoreDTO)
    }
}