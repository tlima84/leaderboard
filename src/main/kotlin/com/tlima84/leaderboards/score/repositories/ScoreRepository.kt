package com.tlima84.leaderboards.score.repositories

import com.tlima84.leaderboards.score.repositories.models.Score
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface ScoreRepository : JpaRepository<Score, Long> {
    @Query("SELECT e FROM Score e ORDER BY e.value ASC, e.createdDate ASC")
    fun findFirst10ByValueAndCreatedDateAsc(): List<Score?>?
}