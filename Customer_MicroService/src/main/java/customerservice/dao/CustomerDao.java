package customerservice.dao;

import customerservice.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CustomerDao extends JpaRepository<Customer, Long> {

    Customer save(Customer customer);
    List<Customer> saveAll(List<Customer> customers);
}
