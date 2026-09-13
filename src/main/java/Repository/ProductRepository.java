package Repository;

import Domain.Product;
import Repository.Mapper.ProductMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ProductRepository {

    private static final String GET_ALL = "SELECT * FROM product ORDER BY productId DESC";
    private static final String GET_BY_ID = "SELECT * FROM product WHERE productId = ?";
    private static final String INSERT_PRODUCT = "INSERT INTO product (categoryId, name, unitPrice, stockQuantity)VALUES (?, ?, ?, ?)";
    private static final String UPDATE_PRODUCT = "UPDATE product SET categoryId = ?,name = ?, unitPrice = ?,stockQuantity = ? WHERE productId = ?";
    private static final String UPDATE_STOCK = "UPDATE product SET stockQuantity = ? WHERE productId = ?";
    private static final String DELETE_PRODUCT = "DELETE FROM product WHERE productId = ?";
    private static final String GET_BY_NAME = "SELECT * FROM product WHERE name = ?";
    private static final String GET_BY_CATEGORY_NAME = """
            SELECT p.productId, p.categoryId, p.name, p.unitPrice, p.stockQuantity
            FROM product p
            JOIN category c ON p.categoryId = c.categoryId
            WHERE c.name = ?
            ORDER BY p.productId DESC
            """;

    private final JdbcTemplate jdbcTemplate;

    public ProductRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Product> getAll() {
        return jdbcTemplate.query(GET_ALL, new ProductMapper());
    }

    public Product getByName(String name) {
        return jdbcTemplate.queryForObject(GET_BY_NAME, new ProductMapper(), name);
    }

    public List<Product> getByCategoryName(String categoryName) {
        return jdbcTemplate.query(GET_BY_CATEGORY_NAME, new ProductMapper(), categoryName);
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
