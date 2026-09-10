package Repository.Mapper;

import Domain.Customer;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerMapper implements RowMapper<Customer> {

    @Override
    public Customer mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Customer(rs.getLong("customerId"), rs.getString("username"), rs.getString("phone"), rs.getString("address"));
    }

}
