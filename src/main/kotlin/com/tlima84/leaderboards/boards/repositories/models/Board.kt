package com.tlima84.leaderboards.boards.repositories.models

import jakarta.persistence.*
import org.springframework.data.jpa.domain.support.AuditingEntityListener


@Entity
@EntityListeners(AuditingEntityListener::class)
@Table(name = "board")
class Board(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0
)
