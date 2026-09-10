package Repository;

import Domain.Customer;
import Repository.Mapper.CustomerMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    private static final String GET_ALL = "SELECT customerId, username, phone, address FROM customer ORDER BY customerId DESC";
    private static final String GET_BY_ID = "SELECT customerId, username, phone, address FROM customer WHERE customerId = ?";
    private static final String INSERT = "INSERT INTO customer (username, phone, address) VALUES (?, ?, ?)" ;
    private static final String UPDATE = "UPDATE customer SET username = ?, phone = ?, address = ? WHERE customerId = ?";
    private static final String DELETE = "DELETE FROM customer WHERE customerId = ?";

    private final JdbcTemplate jdbcTemplate;

    public CustomerRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Customer> getAll() {
        return jdbcTemplate.query(GET_ALL,  new CustomerMapper());
    }

    public Customer getById(Long customerId) {
        return jdbcTemplate.queryForObject(GET_BY_ID,new CustomerMapper(), customerId);
    }

    public int save(Customer customer) {
        return jdbcTemplate.update(INSERT, new CustomerMapper(), customer.getUsername(), customer.getPhone(), customer.getAddress());
    }

    public int update(Customer customer) {
        return jdbcTemplate.update(UPDATE,  new CustomerMapper(), customer.getUsername(), customer.getPhone(), customer.getAddress());
    }

    public int delete(Long customerId) {
        return jdbcTemplate.update(DELETE, new CustomerMapper(), customerId);

    }


}
