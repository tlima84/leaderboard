package com.tlima84.leaderboards.score.services

import com.tlima84.leaderboards.score.controllers.dto.CreateScoreDTO
import com.tlima84.leaderboards.score.controllers.dto.LeaderboardDTO
import com.tlima84.leaderboards.score.controllers.dto.ScoreDTO
import com.tlima84.leaderboards.score.repositories.ScoreRepository
import com.tlima84.leaderboards.score.repositories.models.Score
import org.springframework.cache.CacheManager
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.PageRequest
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.Month

@Service
open class ScoreServiceImpl(private val scoreRepository: ScoreRepository, private val cacheManager: CacheManager) : ScoreService{
    override fun addScore(scoreDTO: CreateScoreDTO): ScoreDTO {
        //save score into database
        val score =  scoreRepository.save(Score(id = 0, value = scoreDTO.value, name = scoreDTO.name))
        //asynchronously verify if there's need to update cache
        this.verifyCacheUpdate(score)
        //returns saved score
        return ScoreDTO(
            id = score.id,
            value = score.value,
            name = score.name,
            createDate = score.createdDate
        )
    }
    @Cacheable("top10AllTime")
    override fun getAllTimeScores(): List<LeaderboardDTO> {
        //get top10 all-time score and add in cache if it does not exist
        return scoreRepository.findFirst10ByValueAndCreatedDateAsc(PageRequest.of(0, 10))?.stream()?.map { entity ->
            entity?.let {
                LeaderboardDTO(
                    id = it.id,
                    name = it.name,
                    value = it.value
                )
            }
        }?.toList() ?: ArrayList()
    }
    override fun updateAllTimeScoreCache() {
        //clear old cache
        cacheManager.getCache("top10AllTime")?.clear()
        //get top10 all-time score and add in cache it
        this.getAllTimeScores()
    }
    override fun getScoreByMonth(month: Month): List<LeaderboardDTO> {
        //creates cacheName by month
        val cacheName = "top10Monthly"+month.name
        //get cache from cache manager
        val cache = cacheManager.getCache(cacheName)
        //try to get stored cache
        var result = cache?.get(cacheName)?.get() as List<LeaderboardDTO>?
        if (result != null) {
            //if there's stored cache return it
            return result
        }
        //if there's not stored cache get it results from database
        result = scoreRepository.findFirst10ByValueAndCreatedDateAscFilteredByMonth(month.value, PageRequest.of(0, 10))?.stream()?.map { entity ->
            entity?.let {
                LeaderboardDTO(
                    id = it.id,
                    name = it.name,
                    value = it.value
                )
            }
        }?.toList() ?: ArrayList()
        //store cache
        cache?.put(cacheName, result)
        //return result after get it from the database
        return result
    }
    override fun updateMonthlyScoreCache(month: Month) {
        //clear old cache
        cacheManager.getCache("top10Monthly")?.clear()
        //get score by month
        this.getScoreByMonth(month)
    }

    @Async
    fun verifyCacheUpdate(score: Score) {
        //get all-time cached value
        val allTime = getAllTimeScores()
        //get monthly cached value
        val monthly = getScoreByMonth(LocalDate.now().month)
        //check if a change is needed in all-time cache
        if(allTime[allTime.size -1].value < score.value){
            this.updateAllTimeScoreCache()
        }
        //check if a change is needed in monthly cache
        if(monthly[monthly.size -1].value < score.value){
            this.updateMonthlyScoreCache(LocalDate.now().month)
        }
    }
}