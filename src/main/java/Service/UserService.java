package Service;

import Domain.UserAccount;
import Repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public int createUser(UserAccount userAccount) {
        if (userRepository.existsByUsername(userAccount.getUsername())) {
            throw new IllegalArgumentException("Username already exists");
        }

        userAccount.setPassword(passwordEncoder.encode(userAccount.getPassword()));
        userAccount.setRole("ROLE_" + userAccount.getRole());

        int userCreated = userRepository.saveUser(userAccount);
        userRepository.saveAuthority(userAccount.getUsername(), userAccount.getRole());

        return userCreated;
    }

    public List<UserAccount> getAll() {
        return userRepository.getAll();
    }
}
