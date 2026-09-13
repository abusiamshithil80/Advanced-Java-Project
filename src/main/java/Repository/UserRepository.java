package Repository;

import Domain.UserAccount;
import Repository.Mapper.UserMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class UserRepository {

    private static final String INSERT_USER = "INSERT INTO users (username, password, enabled) VALUES (?, ?, ?)";
    private static final String INSERT_AUTHORITY = "INSERT INTO authorities (username, authority) VALUES (?, ?)";
    private static final String USER_EXISTS = "SELECT COUNT(*) FROM users WHERE username = ?";
    private static final String GET_ALL_USERS = """
            SELECT u.username, u.enabled, a.authority AS role
            FROM users u
            JOIN authorities a ON u.username = a.username
            ORDER BY u.username
            """;

    private final JdbcTemplate jdbcTemplate;

    public UserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public boolean existsByUsername(String username) {
        Integer count = jdbcTemplate.queryForObject(USER_EXISTS, Integer.class, username);
        return count != null && count > 0;
    }

    public int saveUser(UserAccount userAccount) {
        return jdbcTemplate.update(
                INSERT_USER,
                userAccount.getUsername(),
                userAccount.getPassword(),
                userAccount.getEnabled()
        );
    }

    public int saveAuthority(String username, String authority) {
        return jdbcTemplate.update(INSERT_AUTHORITY, username, authority);
    }

    public List<UserAccount> getAll() {
        return jdbcTemplate.query(GET_ALL_USERS, new UserMapper());
    }
}
