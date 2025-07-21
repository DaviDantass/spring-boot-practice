package dev.davi.coursespring.config;

import dev.davi.coursespring.entities.Category;
import dev.davi.coursespring.entities.Order;
import dev.davi.coursespring.entities.Product;
import dev.davi.coursespring.entities.User;
import dev.davi.coursespring.entities.enums.OrderStatus;
import dev.davi.coursespring.repository.CategoryRepository;
import dev.davi.coursespring.repository.OrderRepository;
import dev.davi.coursespring.repository.ProductRepository;
import dev.davi.coursespring.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.time.Instant;
import java.util.Arrays;

@Configuration
@Profile("test")
public class TestConfig implements CommandLineRunner {

    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final CategoryRepository categoryRepository;
    private final ProductRepository productRepository;

    public TestConfig(UserRepository userRepository, OrderRepository orderRepository, CategoryRepository categoryRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        Category cat1 = new Category(null, "Electronics");
        Category cat2 = new Category(null, "Books");
        Category cat3 = new Category(null, "Computers");

        Product p1 = new Product(null, "Harry Potter and the Sorcerer's Stone",
                "The first book in the Harry Potter series, full of magic and adventure.",
                49.90, "");
        Product p2 = new Product(null, "Samsung Galaxy S21 Smartphone",
                "Smartphone with 6.2\" AMOLED display, triple camera, and long-lasting battery.",
                899.99, "");
        Product p3 = new Product(null, "Dell Inspiron 15 Laptop",
                "Laptop with Intel i7 processor, 16GB RAM, and 512GB SSD.",
                1199.99, "");
        Product p4 = new Product(null, "JBL Tune 500BT Headphones",
                "Bluetooth headphones with excellent sound quality and long battery life.",
                99.99, "");
        Product p5 = new Product(null, "Clean Code - Robert C. Martin",
                "Classic book on software craftsmanship and best programming practices.",
                39.99, "");

        categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3));
        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Carlos Eduardo", "carlos.eduardo@gmail.com", "11987654321", "password123");
        User u2 = new User(null, "Fernanda Silva", "fernanda.silva@gmail.com", "21999887766", "password456");

        Order o1 = new Order(null, Instant.now(), u1, OrderStatus.SHIPPED);
        Order o2 = new Order(null, Instant.now(), u2, OrderStatus.DELIVERED);
        Order o3 = new Order(null, Instant.now(), u1, OrderStatus.DELIVERED);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));
    }
}
