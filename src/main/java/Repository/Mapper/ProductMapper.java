package Repository.Mapper;

import Domain.Product;


import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

public class ProductMapper implements RowMapper<Product> {
    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Product(rs.getLong("productId"), rs.getLong("categoryId"), rs.getString("name"), rs.getFloat("unitPrice"), rs.getInt("stockQuantity"));
    }


}
