package Repository.Mapper;

import Domain.Cashier;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class CashierMapper implements RowMapper<Cashier> {

    @Override
    public Cashier mapRow(ResultSet rs, int rowNum) throws SQLException{
        return new Cashier(rs.getLong("cashierId"), rs.getString("username"), rs.getString("phone") );
    }
}
