package com.tlima84.leaderboards.score.services

import com.tlima84.leaderboards.score.controllers.dto.CreateScoreDTO
import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO
import java.time.Month

interface ScoreService {
    fun addScore(scoreDTO: CreateScoreDTO):ScoreDTO
    fun getAllTimeScores() : List<LeaderboardDTO>

    fun updateAllTimeScoreCache()

    fun getScoreByMonth(month: Month) : List<LeaderboardDTO>

    fun updateMonthlyScoreCache(month: Month)
}