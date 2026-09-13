package Repository.Mapper;

import Domain.Sale;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleMapper implements RowMapper<Sale> {

    @Override
    public Sale mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Sale(rs.getLong("saleId"), rs.getString("cashierUsername"), rs.getObject("customerId", Long.class), rs.getTimestamp("saleDate").toLocalDateTime(),rs.getFloat("totalAmount"));
    }
}
