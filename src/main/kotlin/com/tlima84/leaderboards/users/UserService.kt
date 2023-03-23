package com.tlima84.leaderboards.users

import org.keycloak.admin.client.KeycloakBuilder
import org.keycloak.representations.idm.CredentialRepresentation
import org.keycloak.representations.idm.UserRepresentation

class UserService {
    fun createUser(username: String, password: String) {
        val keycloak = KeycloakBuilder.builder()
            .serverUrl("http://localhost:9090/auth")
            .realm("leaderboard")
            .clientId("leaderboard-client")
            .username("admin-username")
            .password("admin-password")
            .build()

        val user = UserRepresentation()
        user.username = username
        user.credentials = listOf(
            CredentialRepresentation().apply {
                type = CredentialRepresentation.PASSWORD
                value = password
                isTemporary = false
            }
        )

        keycloak.realm("leaderboard").users().create(user)
    }
}