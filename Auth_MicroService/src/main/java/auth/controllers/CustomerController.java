package auth.controllers;


import auth.entities.dto.Customer;
import auth.entities.dto.Order;
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
@RequestMapping("/user/customer")
public class CustomerController {

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/fetchall")
    public List<Customer> getCustomers(){
        String url = "http://localhost:8085/customer/fetchall";
        ResponseEntity<List<Customer>> customersResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){}
            );
            return customersResponse.getBody();
    }
    @GetMapping("/batch/addall")
    public List<Customer> addCustomers(){
        String url = "http://localhost:8085/customer/batch/addall";
        ResponseEntity<List<Customer>> customersResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){}
        );
        return customersResponse.getBody();
    }
    @GetMapping("//addall")
    public List<Customer> getOrdersByCustomer(){
        String url = "http://localhost:8085/customer/batch/addall";
        ResponseEntity<List<Customer>> customersResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){}
        );
        return customersResponse.getBody();
    }
    @GetMapping("order/{customerId}")
    public List<Order> getOrdersByCustomer(int customerId){
        String url = "http://localhost:8085/customer/order/{customerId}";
        ResponseEntity<List<Order>> ordersResponse = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                new ParameterizedTypeReference<>(){},
                customerId
        );
        return ordersResponse.getBody();

    }

}
