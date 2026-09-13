package Repository;


import Domain.Cashier;
import Repository.Mapper.CashierMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CashierRepository {

    private static final String GET_ALL = "SELECT cashierId, username, phone FROM cashier ORDER BY cashierId DESC";
    private static final String GET_BY_ID = "SELECT cashierId, username, phone FROM cashier WHERE cashierId = ?";
    private static final String INSERT = "INSERT INTO cashier (username, phone) VALUES (?, ?)" ;
    private static final String UPDATE = "UPDATE cashier SET username = ?, phone = ? WHERE cashierId= ?";
    private static final String DELETE = "DELETE FROM cashier WHERE cashierId = ?";

    private final JdbcTemplate jdbcTemplate;

    public CashierRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Cashier> getAll() {
        return jdbcTemplate.query(GET_ALL,  new CashierMapper());
    }

    public Cashier getById(Long cashierId) {
        return jdbcTemplate.queryForObject(GET_BY_ID,new CashierMapper(), cashierId);
    }

    public int save(Cashier cashier) {
        return jdbcTemplate.update(INSERT, cashier.getUsername(), cashier.getPhone());
    }

    public int update(Cashier cashier) {
        return jdbcTemplate.update(UPDATE, cashier.getUsername(), cashier.getPhone(), cashier.getCashierId());
    }

    public int delete(Long cashierId) {
        return jdbcTemplate.update(DELETE, cashierId);

    }
}
