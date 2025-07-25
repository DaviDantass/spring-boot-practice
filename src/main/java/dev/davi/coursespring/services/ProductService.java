package dev.davi.coursespring.services;

import dev.davi.coursespring.entities.Product;
import dev.davi.coursespring.exceptions.ProductNotFoundException;
import dev.davi.coursespring.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;  // variável com p minúsculo

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> findAll() {
        return productRepository.findAll();
    }

    public Product findById(Long id) {
        return productRepository.findById(id)
                .orElseThrow(() -> new ProductNotFoundException("Product not found. Id: " + id));
    }
}
