package customerservice.controllers;

import customerservice.entities.Customer;
import customerservice.entities.Order;
import customerservice.entities.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import customerservice.services.CustomerService;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    CustomerService customerService;

    @Autowired
    RestTemplate restTemplate;


    @GetMapping("/fetchall")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getCustomers();
        return ResponseEntity.ok(customers);

    }

    @GetMapping("/batch/addall")
    public ResponseEntity<List<Customer>> addAllCustomers() {
        List<Customer> customers = customerService.addCustomers();
        return ResponseEntity.ok(customers);

    }

    @GetMapping("{customerId}/order")
    public List<Order> getOrdersByCustomer(int customerId){
        String url = "http://localhost:8086/order/{customerId}/fetchall";
            ResponseEntity<List<Order>> ordersResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>(){},
                    customerId
            );
            return ordersResponse.getBody();

    }
    @GetMapping("/{customerId}/order/{orderId}/product")
    public List<Product> getProductsByOrder(@PathVariable int customerId, @PathVariable int orderId ) {
        String url = "http://localhost:8082/product/{customerId}/order/{orderId}";
        ResponseEntity<List<Product>> productsResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>() {
                },
                customerId,orderId
        );
        return productsResponse.getBody();

    }
}