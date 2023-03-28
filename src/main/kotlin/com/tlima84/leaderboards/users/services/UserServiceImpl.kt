package com.tlima84.leaderboards.users.services

import com.tlima84.leaderboards.users.controllers.dto.UserDTO
import org.springframework.stereotype.Service

@Service
class UserServiceImpl : UserService {
    override fun createUser(username: String, password: String) : UserDTO {
        return UserDTO()
    }
}