package productservice.Controllers;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import productservice.entities.Product;
import productservice.services.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping("/{customerId}/order/{orderId}")
    public ResponseEntity<List<Product>> getProductSByOrder(@PathVariable int customerId, @PathVariable int orderId) {
        List<Product> products = productService.getProductsByOrders(customerId, orderId);
        return ResponseEntity.ok(products);

    }

    @GetMapping("/fetchall")
    public ResponseEntity<List<Product>> getAllProducts() {
        List<Product> products = productService.getProducts();
        return ResponseEntity.ok(products);
    }
}
