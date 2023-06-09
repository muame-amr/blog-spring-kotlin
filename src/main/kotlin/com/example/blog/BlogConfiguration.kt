package com.example.blog

import com.example.blog.entity.Article
import com.example.blog.entity.User
import com.example.blog.repository.ArticleRepository
import com.example.blog.repository.UserRepository
import org.springframework.boot.ApplicationRunner
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class BlogConfiguration {

    @Bean
    fun databaseInitializer(
        userRepository: UserRepository,
        articleRepository: ArticleRepository
    ) = ApplicationRunner {
        val johnDoe = userRepository.save(User("johnDoe", "John", "Doe"))
        articleRepository.save(Article(
            title = "Lorem",
            headline = "Ipsum",
            content = "Dolor Sit Amet",
            author = johnDoe
        ))
        articleRepository.save(Article(
            title = "Ipsum",
            headline = "Lorem",
            content = "consectetur adipiscing elit",
            author = johnDoe
        ))
    }
}