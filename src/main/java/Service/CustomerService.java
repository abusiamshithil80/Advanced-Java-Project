package Service;

import Domain.Customer;
import Repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public List<Customer> getAll() {
        return customerRepository.getAll();
    }

    public Customer getById(Long customerId) {
        return customerRepository.getById(customerId);
    }

    public int save(Customer customer) {
        return customerRepository.save(customer);
    }

    public boolean update(Long customerId, Customer customer) {
        customer.setCustomerId(customerId);
        return customerRepository.update(customer) > 0;
    }

    public boolean delete(Long customerId) {
        return customerRepository.delete(customerId) > 0;
    }
}
