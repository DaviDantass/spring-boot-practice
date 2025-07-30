package dev.davi.coursespring.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.davi.coursespring.entities.pk.OrderItemPk;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;

import java.io.Serializable;
import java.util.Objects;

@Entity
public class OrderItem implements Serializable {
    private static final long serialVersionUID = 1L;

    @EmbeddedId
    private OrderItemPk id = new OrderItemPk();
    private int quantity;
    private double price;

    public OrderItem() {
    }

    public OrderItem(Order order, Product product, int quantity, double price) {
        id.setOrder(order);
        id.setProduct(product);
        this.price = price;
        this.quantity = quantity;
    }
    @JsonIgnore
    public Order getOrder() {
        return id.getOrder();
    }
    public void setOrder(Order order) {
        id.setOrder(order);
    }

    public Product getProduct() {
        return id.getProduct();
    }

    public void setProduct(Product product) {
        id.setProduct(product);
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Double getSubTotal(){
        return price * quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        OrderItem orderItem = (OrderItem) o;
        return quantity == orderItem.quantity && Double.compare(price, orderItem.price) == 0 && Objects.equals(id, orderItem.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
