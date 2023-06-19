package orderservice.dao;

import orderservice.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OrderDao extends JpaRepository<Order,Long> {

    Order save(Order customer);
    List<Order> saveAll(List<Order> customers);
}
