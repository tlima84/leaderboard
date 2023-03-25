package com.tlima84.leaderboards.score.repositories.models

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.io.Serializable
import java.time.LocalDateTime


@Entity
@Table(name = "score")
@EntityListeners(AuditingEntityListener::class)
class Score(
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long = 0,
    var name: String,
    var value: Long
): Serializable {

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    lateinit var createdDate: LocalDateTime
}
