package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.ArticleRequestDto;
import com.stockmanagment.app.dto.ArticleResponseDto;
import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.model.Equipement;
import com.stockmanagment.app.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleResponseDto createArticle(ArticleRequestDto requestDto) {
        Article article = convertToEntity(requestDto);
        article.setDate_entree(Instant.now()); // Set the entry date

        Article savedArticle = articleRepository.save(article);
        return convertToResponseDto(savedArticle);
    }

    public ArticleResponseDto findById(Long id) throws ChangeSetPersister.NotFoundException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        return convertToResponseDto(article);
    }

    public List<ArticleResponseDto> findAllArticles() {
        List<Article> articles = articleRepository.findAll();
        return articles.stream()
                .map(this::convertToResponseDto)
                .collect(Collectors.toList());
    }

    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto requestDto) throws ChangeSetPersister.NotFoundException {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        existingArticle.setReference(requestDto.getReference());
        existingArticle.setMatricule(requestDto.getMatricule());

        Article updatedArticle = articleRepository.save(existingArticle);
        return convertToResponseDto(updatedArticle);
    }

    public void deleteArticle(Long id) throws ChangeSetPersister.NotFoundException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        articleRepository.delete(article);
    }

    private Article convertToEntity(ArticleRequestDto requestDto) {
        return Article.builder()
                .reference(requestDto.getReference())
                .matricule(requestDto.getMatricule())
                .build();
    }

    private ArticleResponseDto convertToResponseDto(Article article) {
        return ArticleResponseDto.builder()
                .id(article.getId())
                .reference(article.getReference())
                .matricule(article.getMatricule())
                .date_entree(article.getDate_entree())
                .date_sortie(article.getDate_sortie())
                .build();
    }
}
