package com.tlima84.leaderboards.config

import io.swagger.v3.oas.models.OpenAPI
import io.swagger.v3.oas.models.info.Info
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.info.BuildProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class SwaggerConfig {

    @Autowired
    private val buildProperties: BuildProperties? = null

    @Bean
    fun customOpenAPI(): OpenAPI? {
        //Add information to the open API UI
        return OpenAPI()
            .info(
                Info().title("Leaderboard API")
                    .version(version())
                    .description("Leaderboard system for social application to register user's scores")
            )
    }

    private fun version(): String? {
        //get version from build properties
        return buildProperties?.version
    }
}