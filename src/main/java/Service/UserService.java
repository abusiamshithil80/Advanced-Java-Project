package Service;

import Domain.UserAccount;
import Domain.Cashier;
import Domain.Customer;
import Repository.CashierRepository;
import Repository.CustomerRepository;
import Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.time.LocalDate;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final CashierRepository cashierRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, CustomerRepository customerRepository,
                       CashierRepository cashierRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
        this.cashierRepository = cashierRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public int createUser(UserAccount userAccount) {
        if (userRepository.existsByUsername(userAccount.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        String role = userAccount.getRole();

        if ((role.equals("CUSTOMER") || role.equals("CASHIER"))
                && (userAccount.getPhone() == null || userAccount.getPhone().isBlank())) {
            throw new IllegalArgumentException("Phone number is required for customer and cashier users");
        }

        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setRole("ROLE_" + role);

        int userCreated = userRepository.saveUser(userAccount);
        userRepository.saveAuthority(userAccount.getUsername(), userAccount.getRole());

        if (role.equals("CUSTOMER")) {
            Customer customer = new Customer(
                    null,
                    userAccount.getUsername(),
                    userAccount.getPhone(),
                    userAccount.getAddress()
            );
            customerRepository.save(customer);
        }

        if (role.equals("CASHIER")) {
            Cashier cashier = new Cashier(
                    null,
                    userAccount.getUsername(),
                    userAccount.getPhone()
            );
            cashierRepository.save(cashier);
        }

        return userCreated;
    }

    public List<UserAccount> getAll() {
        return userRepository.getAll();
    }
}
