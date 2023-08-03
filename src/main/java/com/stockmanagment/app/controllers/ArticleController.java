package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.ArticleRequestDto;
import com.stockmanagment.app.dto.ArticleResponseDto;
import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    @Autowired
    private ArticleService articleService;

    @PostMapping
    public ResponseEntity<ArticleResponseDto> createArticle(@RequestBody ArticleRequestDto requestDto) {
        ArticleResponseDto responseDto = articleService.createArticle(requestDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(responseDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> getArticleById(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        ArticleResponseDto responseDto = articleService.findById(id);
        return ResponseEntity.ok(responseDto);
    }

    @GetMapping
    public ResponseEntity<List<ArticleResponseDto>> getAllArticles() {
        List<ArticleResponseDto> responseDtoList = articleService.findAllArticles();
        return ResponseEntity.ok(responseDtoList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleResponseDto> updateArticle(
            @PathVariable Long id, @RequestBody ArticleRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        ArticleResponseDto responseDto = articleService.updateArticle(id, requestDto);
        return ResponseEntity.ok(responseDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteArticle(@PathVariable Long id) throws ChangeSetPersister.NotFoundException {
        articleService.deleteArticle(id);
        return ResponseEntity.noContent().build();
    }
}