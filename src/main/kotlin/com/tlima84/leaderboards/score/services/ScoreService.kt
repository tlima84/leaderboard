package com.tlima84.leaderboards.score.services

import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO

interface ScoreService {
    fun addScore(scoreDTO: ScoreDTO):ScoreDTO
    fun getAlltimeScores() : List<LeaderboardDTO>

    fun updateAlltimeScore(): List<LeaderboardDTO>
}