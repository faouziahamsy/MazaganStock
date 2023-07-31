package com.stockmanagment.app.service;

import com.stockmanagment.app.model.Equipement_Article;
import com.stockmanagment.app.repository.Equipement_ArticleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class Equipement_ArticleService {

  /*  private final Equipement_ArticleRepository equipement_ArticleRepository;

    @Autowired
    public Equipement_ArticleService(Equipement_ArticleRepository equipement_ArticleRepository) {
        this.equipement_ArticleRepository = equipement_ArticleRepository;
    }

    public List<Equipement_Article> getAllEquipement_Articles() {
        return equipement_ArticleRepository.findAll();
    }

    public Optional<Equipement_Article> getEquipement_ArticleById(Long id) {
        return equipement_ArticleRepository.findById(id);
    }

    // Add methods for creating, updating, and deleting Equipement_Article entities if needed
    */

}