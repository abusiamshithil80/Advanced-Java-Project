package Repository;

import Domain.Product;
import Repository.Mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class ProductRepository {

    private static final String GET_ALL = "SELECT * FROM product ORDER BY productId DESC";
    private static final String GET_BY_ID = "SELECT * FROM product WHERE productId = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO product (categoryId, name, unitPrice, stockQuantity)VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET categoryId = ?,name = ?, unitPrice = ?,stockQuantity = ? WHERE productId = ?";
    private static final String UPDATE_STOCK = "UPDATE product SET stockQuantity = ? WHERE productId = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE productId = ?";

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAll() {
        return jdbcTemplate.query(GET_ALL, new ProductMapper());
    }



    public Product getById(Long productId) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new ProductMapper(), productId);
    }

    public int save(Product product) {
        return jdbcTemplate.update(INSERT_PRODUCT, product.getCategoryId(), product.getName(), product.getUnitPrice(), product.getStockQuantity());
    }

    public int update(Product product) {
        return jdbcTemplate.update(UPDATE_PRODUCT, product.getCategoryId(), product.getName(), product.getUnitPrice(), product.getStockQuantity(), product.getProductId());
    }

    public int updateStock(Long productId, Integer stockQuantity) {

        return jdbcTemplate.update(UPDATE_STOCK, stockQuantity, productId);
    }

    public int delete(Long productId) {
        return jdbcTemplate.update(DELETE_PRODUCT, productId);
    }

}
