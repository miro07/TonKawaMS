package auth.controllers;


import auth.entities.dto.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/user/product")
public class ProductController {
    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/fetchall")
    public List<Product> getProducts() {
        String url = "http://localhost:8082/product/fetchall";
        ResponseEntity<List<Product>> productsResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                }
        );
        return productsResponse.getBody();

    }
}
