package com.example.blog.repository

import com.example.blog.entity.User
import org.springframework.data.repository.CrudRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository: CrudRepository<User, Long> {
    fun findByLogin(login: String): User?
}