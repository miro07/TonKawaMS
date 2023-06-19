package productservice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import productservice.entities.Product;
import productservice.exceptions.ProductException;

import java.util.List;

public class ProductService {

    @Autowired
    RestTemplate restTemplate;

    public List<Product> getProductsByOrders(int customerId, int orderId){
        String url = "https://615f5fb4f7254d0017068109.mockapi.io/api/v1/customers/{customerId}/orders/{orderId}/products";
        try {
            ResponseEntity<List<Product>> ordersResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>(){},
                    customerId,orderId
            );
            return ordersResponse.getBody();
        }
        catch (Exception e) {
            throw new ProductException("Error fetching products", e);
        }
    }

    public List<Product> getProducts(){
        String url = "https://615f5fb4f7254d0017068109.mockapi.io/api/v1/products";
        try {
            ResponseEntity<List<Product>> productsResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>(){}
            );
            return productsResponse.getBody();
        }
        catch (Exception e) {
            throw new ProductException("Error fetching products", e);
        }



    }
}