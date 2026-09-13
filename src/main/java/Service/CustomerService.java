package Service;

import Domain.Customer;
import Domain.Sale;
import Repository.CustomerRepository;
import Repository.SaleRepository;
import Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final SaleRepository saleRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public CustomerService(CustomerRepository customerRepository, SaleRepository saleRepository,
                           UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.customerRepository = customerRepository;
        this.saleRepository = saleRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
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

    public List<Sale> getMyPurchases(String username) {
        List<Customer> customers = customerRepository.getByUsername(username);

        if (customers.isEmpty()) {
            return List.of();
        }

        return saleRepository.getByCustomerId(customers.get(0).getCustomerId());
    }

    public int updatePassword(String username, String newPassword) {
        return userRepository.updatePassword(username, passwordEncoder.encode(newPassword));
    }
}
