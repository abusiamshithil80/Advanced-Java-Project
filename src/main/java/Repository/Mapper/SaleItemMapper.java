package Repository.Mapper;

import Domain.SaleItem;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class SaleItemMapper implements RowMapper<SaleItem> {

    @Override
    public SaleItem mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new SaleItem(
                rs.getLong("saleItemId"),
                rs.getLong("productId"),
                rs.getInt("quantity"),
                rs.getFloat("unitPrice"),
                rs.getFloat("subtotal")
        );
    }
}
