package com.tlima84.leaderboards.score.repositories

import com.tlima84.leaderboards.score.repositories.models.Score
import org.springframework.data.domain.Pageable
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param

interface ScoreRepository : JpaRepository<Score, Long> {
    @Query("SELECT e FROM Score e ORDER BY e.value DESC, e.createdDate ASC")
    fun findFirst10ByValueAndCreatedDateAsc(pageable: Pageable): List<Score>?

    @Query(
        "SELECT e FROM Score e WHERE MONTH(e.createdDate) = :month " +
                "ORDER BY e.value DESC, e.createdDate ASC"
    )
    fun findFirst10ByValueAndCreatedDateAscFilteredByMonth(
        @Param("month") month: Int, pageable: Pageable
    ): List<Score>?
}