package Repository;

import Domain.Category;
import Repository.Mapper.CategoryMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CategoryRepository {

    private static final String GET_ALL = "SELECT categoryId, name FROM category ORDER BY categoryId DESC";
    private static final String GET_BY_ID = "SELECT categoryId, name FROM category WHERE categoryId = ?";
    private static final String INSERT = "INSERT INTO category (name) VALUES (?)";
    private static final String UPDATE = "UPDATE category SET name = ? WHERE categoryId = ?";
    private static final String DELETE = "DELETE FROM category WHERE categoryId = ?";

    private final JdbcTemplate jdbcTemplate;

    public CategoryRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<Category> getAll() {
        return jdbcTemplate.query(GET_ALL, new CategoryMapper());
    }

    public Category getById(Long categoryId) {
        return jdbcTemplate.queryForObject(GET_BY_ID, new CategoryMapper(), categoryId);
    }

    public int save(Category category) {
        return jdbcTemplate.update(INSERT, category.getName());
    }

    public int update(Category category) {
        return jdbcTemplate.update(UPDATE, category.getName(), category.getCategoryId());
    }

    public int delete(Long categoryId) {
        return jdbcTemplate.update(DELETE, categoryId);
    }
}
