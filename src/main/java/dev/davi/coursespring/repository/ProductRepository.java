package dev.davi.coursespring.repository;

import dev.davi.coursespring.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
