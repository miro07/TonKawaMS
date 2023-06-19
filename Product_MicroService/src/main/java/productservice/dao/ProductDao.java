package productservice.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import productservice.entities.Product;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> {
}
