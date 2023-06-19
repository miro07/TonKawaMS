package customerservice.services;

import customerservice.dao.CustomerDao;
import customerservice.entities.Customer;
import customerservice.exceptions.CustomerException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.List;

public class CustomerService {
    @Autowired
    CustomerDao customerDao;

    @Autowired
    RestTemplate restTemplate;

    public Customer addCustomer(Customer customer){
        return customerDao.save(customer);
    }

    public List<Customer> addCustomers() {
        List<Customer> customers = getCustomers();
        return customerDao.saveAll(customers);}

    public List<Customer> getCustomers(){
        String url = "https://615f5fb4f7254d0017068109.mockapi.io/api/v1/customers";
        try {
            ResponseEntity<List<Customer>> customersResponse = restTemplate.exchange(
                    url,
                    HttpMethod.GET,
                    null,
                    new ParameterizedTypeReference<>(){}
            );
            return customersResponse.getBody();
        }
        catch (Exception e) {
            throw new CustomerException("Error fetching customers", e);
        }
    }

}

