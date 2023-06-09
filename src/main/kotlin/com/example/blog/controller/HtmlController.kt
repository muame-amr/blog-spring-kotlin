package com.example.blog.controller

import com.example.blog.BlogProperties
import com.example.blog.entity.Article
import com.example.blog.entity.RenderedArticle
import com.example.blog.format
import com.example.blog.repository.ArticleRepository
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.server.ResponseStatusException

@Controller
class HtmlController(
    private val articleRepository: ArticleRepository, private val properties: BlogProperties
) {

    @GetMapping("/")
    fun blog(model: Model): String {
        model["title"] = properties.title
        model["banner"] = properties.banner
        model["articles"] = articleRepository.findAllByOrderByAddedAtDesc().map {
            it.render()
        }
        return "blog"
    }

    @GetMapping("/article/{slug}")
    fun article(@PathVariable slug: String, model: Model): String {
        val article = articleRepository.findBySlug(slug)?.render() ?: throw ResponseStatusException(
            HttpStatus.NOT_FOUND,
            "This article does not exists"
        )
        model["title"] = article.title
        model["article"] = article
        return "article"

    }

    fun Article.render() = RenderedArticle(
        slug, title, headline, content, author, addedAt.format()
    )
}