package dev.davi.coursespring.services;

import dev.davi.coursespring.entities.Order;
import dev.davi.coursespring.repository.OrderRepository;
import dev.davi.coursespring.services.exceptions.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {
    private final OrderRepository orderRepository;


    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public List<Order> findAll() {
        return  orderRepository.findAll();
    }

    public Order findById(Long id) {
        return orderRepository.findById(id).orElseThrow(() -> new  ResourceNotFoundException("Pedido não encontrado com id " + id));
    }


}
