package com.tlima84.leaderboards.boards.repositories

import com.tlima84.leaderboards.boards.repositories.models.Board
import org.springframework.data.jpa.repository.JpaRepository

interface BoardsRepository : JpaRepository<Board, Long> {
}