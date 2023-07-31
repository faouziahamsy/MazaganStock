package com.stockmanagment.app.controllers;

import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/articles")
public class ArticleController {
    private final ArticleService articleService;

    @Autowired
    public ArticleController(ArticleService articleService) {
        this.articleService = articleService;
    }

    @GetMapping
    public List<Article> getAllArticles() {
        return articleService.getAllArticles();
    }

    @GetMapping("/{id}")
    public Article getArticleById(@PathVariable Long id) {
        return articleService.getArticleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Article not found with id: " + id));
    }

    @PostMapping
    public Article createArticle(@RequestBody Article article) {
        return articleService.createArticle(article);
    }

    @PutMapping("/{id}")
    public Article updateArticle(@PathVariable Long id, @RequestBody Article articleDetails) {
        return articleService.updateArticle(id, articleDetails)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Article not found with id: " + id));
    }

    @DeleteMapping("/{id}")
    public boolean deleteArticle(@PathVariable Long id) {
        return articleService.deleteArticle(id);
    }
}