package dev.davi.coursespring.config;

import dev.davi.coursespring.entities.*;
import dev.davi.coursespring.entities.enums.OrderStatus;
import dev.davi.coursespring.repository.*;
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
    private final OrderItemRepository orderItemRepository;

    public TestConfig(UserRepository userRepository, OrderRepository orderRepository, CategoryRepository categoryRepository, ProductRepository productRepository, OrderItemRepository orderItemRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.categoryRepository = categoryRepository;
        this.productRepository = productRepository;
        this.orderItemRepository = orderItemRepository;
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

        p1.getCategories().add(cat2);
        p2.getCategories().add(cat1);
        p2.getCategories().add(cat3);
        p3.getCategories().add(cat3);
        p4.getCategories().add(cat1);
        p5.getCategories().add(cat2);

        productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5));

        User u1 = new User(null, "Carlos Eduardo", "carlos.eduardo@gmail.com", "11987654321", "password123");
        User u2 = new User(null, "Fernanda Silva", "fernanda.silva@gmail.com", "21999887766", "password456");

        Order o1 = new Order(null, Instant.parse("2025-07-01T10:00:00Z"), u1, OrderStatus.PAID);
        Order o2 = new Order(null, Instant.parse("2025-07-02T12:30:00Z"), u2, OrderStatus.DELIVERED);
        Order o3 = new Order(null, Instant.parse("2025-07-03T09:15:00Z"), u1, OrderStatus.DELIVERED);

        userRepository.saveAll(Arrays.asList(u1, u2));
        orderRepository.saveAll(Arrays.asList(o1, o2, o3));

        OrderItem oi1 = new OrderItem(o1, p1, 2, p1.getPrice());
        OrderItem oi2 = new OrderItem(o1, p3, 1, p3.getPrice());
        OrderItem oi3 = new OrderItem(o2, p3, 2, p3.getPrice());
        OrderItem oi4 = new OrderItem(o3, p5, 2, p5.getPrice());

        orderItemRepository.saveAll(Arrays.asList(oi1, oi2, oi3, oi4));

        Payment pay1 = new Payment(null, Instant.parse("2025-07-01T12:00:00Z"), o1);

        o1.setPayment(pay1);
        orderRepository.save(o1);
    }
}
