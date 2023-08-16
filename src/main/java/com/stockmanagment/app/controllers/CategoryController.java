package com.stockmanagment.app.controllers;

import com.stockmanagment.app.dto.CategoryRequestDto;
import com.stockmanagment.app.dto.CategoryResponseDto;
import com.stockmanagment.app.model.Category;
import com.stockmanagment.app.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.stream.Collectors;
@CrossOrigin
@RestController
@RequestMapping("/categories")
public class CategoryController {

    private final CategoryService categoryService;
    @Autowired
    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }
    @CrossOrigin
    @GetMapping
    public List<Category> getAllCategories() {
        return categoryService.getAllCategories();
    }
    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Category> getCategoryById(@PathVariable Long id) {
        return categoryService.getCategoryById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin
    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public CategoryResponseDto createCategory(@RequestBody CategoryRequestDto category) {
        //recup data for request (client)
        String nom= category.getNom();
        //creer objet category
        Category cat = new Category();
        //recup cat (name)
        cat.setNom(nom);
        //creer objet dto
        CategoryResponseDto res = new CategoryResponseDto();
        //name to objet dto
        res.setNom(categoryService.createCategory(cat).getNom());
        System.out.println("------------------------>"+category);
        return res;

    }
    @CrossOrigin
    @PutMapping("/{id}")
    public ResponseEntity<Category> updateCategory(@PathVariable Long id, @RequestBody Category category) {
        return categoryService.updateCategory(id, category)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @CrossOrigin
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id) {
        if (categoryService.deleteCategory(id)) {
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @CrossOrigin
    @GetMapping("/all")
    public ResponseEntity<Long> getTotalCategories() {
        long totalCategories = categoryService.getTotalCategories();
        return ResponseEntity.ok(totalCategories);
    }
}
