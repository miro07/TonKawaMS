package productservice.entities;

import java.math.BigDecimal;
import java.util.Objects;

public class ProductDetails {
    private BigDecimal price;



    private String description;

    public ProductDetails(BigDecimal price, String description, String color) {
        this.price = price;
        this.description = description;
        this.color = color;
    }
    public ProductDetails() {
    }
    private String color;


    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ProductDetails that = (ProductDetails) o;
        return price.equals(that.price) && description.equals(that.description) && color.equals(that.color);
    }

    @Override
    public int hashCode() {
        return Objects.hash(price, description, color);
    }
}
