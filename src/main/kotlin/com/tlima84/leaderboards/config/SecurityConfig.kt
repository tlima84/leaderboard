package com.tlima84.leaderboards.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.web.SecurityFilterChain


@Configuration
class SecurityConfig {
    @Bean
    @Throws(Exception::class)
    fun filterChain(http: HttpSecurity): SecurityFilterChain {
        http
            .authorizeHttpRequests { authorizeRequests ->
//                authorizeRequestsauthorizeRequests.requestMatchers(HttpMethod.GET, "/v1/leaderboards/**").permitAll()
                authorizeRequests.anyRequest().permitAll()
            }
            .httpBasic()
            .and()
            .csrf().disable()

        return http.build()
    }
}