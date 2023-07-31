package com.stockmanagment.app.controllers;

import com.stockmanagment.app.model.Equipement_Article;
import com.stockmanagment.app.service.Equipement_ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/equipement_articles")
public class Equipement_ArticleController {
   /* private final Equipement_ArticleService equipement_ArticleService;

    @Autowired
    public Equipement_ArticleController(Equipement_ArticleService equipement_ArticleService) {
        this.equipement_ArticleService = equipement_ArticleService;
    }

    @GetMapping
    public List<Equipement_Article> getAllEquipement_Articles() {
        return equipement_ArticleService.getAllEquipement_Articles();
    }

    @GetMapping("/{id}")
    public Equipement_Article getEquipement_ArticleById(@PathVariable Long id) {
        return equipement_ArticleService.getEquipement_ArticleById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Equipement_Article not found with id: " + id));
    }
*/
    // Add other endpoints for CRUD operations related to Equipement_Article entity
}