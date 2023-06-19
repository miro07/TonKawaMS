package customerservice.entities;

import java.util.List;
import java.util.Objects;

public class Product {
    private String createdAt;
    private String name;
    private int stock;
    private int id;

    private List<Integer> orderId;
    private ProductDetails details;

    public Product(String createdAt, String name, int stock, int id, ProductDetails details) {
        this.createdAt = createdAt;
        this.name = name;
        this.stock = stock;
        this.id = id;
        this.details = details;
    }
    public Product() {}
    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ProductDetails getDetails() {
        return details;
    }

    public void setDetails(ProductDetails details) {
        this.details = details;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return stock == product.stock && id == product.id && createdAt.equals(product.createdAt) && name.equals(product.name) && details.equals(product.details);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createdAt, name, stock, id, details);
    }
}
