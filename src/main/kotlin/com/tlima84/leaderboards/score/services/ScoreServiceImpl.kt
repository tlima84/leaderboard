package com.tlima84.leaderboards.score.services

import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO
import com.tlima84.leaderboards.score.repositories.ScoreRepository
import com.tlima84.leaderboards.score.repositories.models.Score
import org.springframework.cache.annotation.CachePut
import org.springframework.cache.annotation.Cacheable
import org.springframework.stereotype.Service

@Service
class ScoreServiceImpl(private val scoreRepository: ScoreRepository) : ScoreService{
    override fun addScore(scoreDTO: ScoreDTO): ScoreDTO {
        val score =  scoreRepository.save(Score(id = 0, value = scoreDTO.value, name = scoreDTO.name))
        this.updateAlltimeScore()
        return ScoreDTO(
            id = score.id,
            value = score.value,
            name = score.name,
            createDate = score.createdDate
        )
    }
    @Cacheable("top10ofMonth")
    override fun getAlltimeScores(): List<LeaderboardDTO> {
        return scoreRepository.findFirst10ByValueAndCreatedDateAsc()?.stream()?.map { entity ->
            entity?.let {
                LeaderboardDTO(
                    id = it.id,
                    name = it.name,
                    value = it.value
                )
            }
        }?.toList() ?: ArrayList()
    }
    @CachePut("top10ofMonth")
    override fun updateAlltimeScore(): List<LeaderboardDTO> {
        val topStudents = scoreRepository.findFirst10ByValueAndCreatedDateAsc()?.stream()?.map { entity ->
            entity?.let {
                LeaderboardDTO(
                    id = it.id,
                    name = it.name,
                    value = it.value
                )
            }
        }?.toList() ?: ArrayList()

        // Return the updated top students list
        return topStudents
    }
}