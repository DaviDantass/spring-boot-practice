package dev.davi.coursespring.services;

import dev.davi.coursespring.entities.Category;
import dev.davi.coursespring.exceptions.CategoryNotFoundException;
import dev.davi.coursespring.repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }

    public Category findById(Long id) {
        return categoryRepository.findById(id)
                .orElseThrow(() -> new CategoryNotFoundException("Category not found. Id: " + id));
    }
}
