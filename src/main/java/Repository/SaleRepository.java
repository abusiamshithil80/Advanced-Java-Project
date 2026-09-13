package Repository;

import Domain.Sale;
import Domain.SaleItem;
import Repository.Mapper.SaleItemMapper;
import Repository.Mapper.SaleMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class SaleRepository {

    private static final String INSERT_SALE = """
            INSERT INTO sales (cashierUsername, customerId, saleDate, totalAmount)
            VALUES (?, ?, ?, ?)
            """;
    private static final String INSERT_SALE_ITEM = """
            INSERT INTO sale_item (saleId, productId, quantity, unitPrice, subtotal)
            VALUES (?, ?, ?, ?, ?)
            """;
    private static final String GET_BY_CASHIER = """
            SELECT saleId, cashierUsername, customerId, saleDate, totalAmount
            FROM sales
            WHERE cashierUsername = ?
            ORDER BY saleDate DESC
            """;
    private static final String GET_BY_CUSTOMER = """
            SELECT saleId, cashierUsername, customerId, saleDate, totalAmount
            FROM sales
            WHERE customerId = ?
            ORDER BY saleDate DESC
            """;
    private static final String GET_ITEMS_BY_SALE = """
            SELECT saleItemId, productId, quantity, unitPrice, subtotal
            FROM sale_item
            WHERE saleId = ?
            ORDER BY saleItemId
            """;

    private final JdbcTemplate jdbcTemplate;

    public SaleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public Long saveSale(Sale sale) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcTemplate.update(connection -> {
            PreparedStatement statement = connection.prepareStatement(INSERT_SALE, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, sale.getCashierUsername());
            statement.setObject(2, sale.getCustomerId());
            statement.setObject(3, sale.getSaleDate());
            statement.setFloat(4, sale.getTotalAmount());
            return statement;
        }, keyHolder);

        if (keyHolder.getKey() == null) {
            throw new IllegalStateException("Sale could not be created");
        }

        return keyHolder.getKey().longValue();
    }

    public int saveSaleItem(Long saleId, SaleItem saleItem) {
        return jdbcTemplate.update(
                INSERT_SALE_ITEM,
                saleId,
                saleItem.getProductId(),
                saleItem.getQuantity(),
                saleItem.getUnitPrice(),
                saleItem.getSubtotal()
        );
    }

    public List<Sale> getByCashierUsername(String cashierUsername) {
        List<Sale> sales = jdbcTemplate.query(GET_BY_CASHIER, new SaleMapper(), cashierUsername);
        for (Sale sale : sales) {
            sale.setItems(getItemsBySaleId(sale.getSaleId()));
        }
        return sales;
    }

    public List<Sale> getByCustomerId(Long customerId) {
        List<Sale> sales = jdbcTemplate.query(GET_BY_CUSTOMER, new SaleMapper(), customerId);
        for (Sale sale : sales) {
            sale.setItems(getItemsBySaleId(sale.getSaleId()));
        }
        return sales;
    }

    public List<SaleItem> getItemsBySaleId(Long saleId) {
        return jdbcTemplate.query(GET_ITEMS_BY_SALE, new SaleItemMapper(), saleId);
    }
}
