package orderservice.services;

import orderservice.dao.OrderDao;
import orderservice.entities.Order;
import orderservice.exception.OrderException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
public class OrderService {
    @Autowired
    RestTemplate restTemplate;

    @Autowired
    OrderDao orderDao;

    public Order addOrder(Order customer){
        return orderDao.save(customer);
    }

    public List<Order> addOrdersByCustomer(int customerId) {
        List<Order> customers = getOrdersByCustomer(customerId);
        return orderDao.saveAll(customers);}

    public List<Order> getOrdersByCustomer(int customerId){
        String url = "https://615f5fb4f7254d0017068109.mockapi.io/api/v1/customers/{customerId}/orders";
        try {
            ResponseEntity<List<Order>> ordersResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>(){},
                    customerId
            );
            return ordersResponse.getBody();
        }
        catch (Exception e) {
            throw new OrderException("Error fetching orders", e);
        }
    }
}
