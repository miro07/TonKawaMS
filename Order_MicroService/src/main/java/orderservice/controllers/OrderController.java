package orderservice.controllers;


import orderservice.entities.Order;
import orderservice.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    OrderService orderService;
    @GetMapping("/{customerId}/fetchall")
    public ResponseEntity<List<Order>> getAllOrders(@PathVariable int customerId) {
        List<Order> orders = orderService.getOrdersByCustomer(customerId);
        return ResponseEntity.ok(orders);

    }

    @GetMapping("/batch/{customerId}/addall")
    public ResponseEntity<List<Order>> addAllOrders(@PathVariable int customerId) {
        List<Order> customers = orderService.addOrdersByCustomer(customerId);
        return ResponseEntity.ok(customers);

    }
}
