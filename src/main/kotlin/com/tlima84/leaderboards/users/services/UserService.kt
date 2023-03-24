package com.tlima84.leaderboards.users.services

import com.tlima84.leaderboards.users.controllers.dto.UserDTO

interface UserService {
    fun createUser(username: String, password: String) : UserDTO
}