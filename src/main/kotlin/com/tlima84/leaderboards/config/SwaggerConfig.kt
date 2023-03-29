package com.tlima84.leaderboards.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Value("\${project.version}")
    private lateinit var version: String

    @Bean
    fun customOpenAPI(): OpenAPI? {
        //Add information to the open API UI
        return OpenAPI()
            .info(
                Info().title("Leaderboard API")
                    .version(version)
                    .description("Leaderboard system for social application to register user's scores")
            )
    }
}