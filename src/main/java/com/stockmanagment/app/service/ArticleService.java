package com.stockmanagment.app.service;

import com.stockmanagment.app.dto.ArticleRequestDto;
import com.stockmanagment.app.dto.ArticleResponseDto;
import com.stockmanagment.app.model.Article;
import com.stockmanagment.app.repository.ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;
import java.time.Instant;
import java.util.List;
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

    public ArticleResponseDto updateArticle(Long id, ArticleRequestDto requestDto)
            throws ChangeSetPersister.NotFoundException {
        Article existingArticle = articleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());

        existingArticle.setQuantity(requestDto.getQuantity());
        existingArticle.setMatricule(requestDto.getMatricule());
        existingArticle.setDate_sortie(Instant.now());
        existingArticle.setEtat(requestDto.getEtat());

        Article updatedArticle = articleRepository.save(existingArticle);
        return convertToResponseDto(updatedArticle);
    }
    public void deleteArticle(Long id) throws ChangeSetPersister.NotFoundException {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new ChangeSetPersister.NotFoundException());
        articleRepository.delete(article);
    }
    private Article convertToEntity(ArticleRequestDto requestDto) {
        Article article = new Article();
        article.setQuantity(requestDto.getQuantity());
        article.setMatricule(requestDto.getMatricule());
        article.setEtat(requestDto.getEtat());
        return article;
    }
    private ArticleResponseDto convertToResponseDto(Article article) {
        ArticleResponseDto responseDto = new ArticleResponseDto();
        responseDto.setId(article.getId());
        responseDto.setQuantity(article.getQuantity());
        responseDto.setMatricule(article.getMatricule());
        responseDto.setEtat(article.getEtat());
        responseDto.setDate_entree(article.getDate_entree());
        responseDto.setDate_sortie(article.getDate_sortie());
        return responseDto;
    }

    public long getTotalArticles() {
        return articleRepository.count();
    }
}
