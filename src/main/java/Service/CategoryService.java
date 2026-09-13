package Service;

import Domain.Category;
import Repository.CategoryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    public List<Category> getAll() {
        return categoryRepository.getAll();
    }

    public Category getById(Long categoryId) {
        return categoryRepository.getById(categoryId);
    }

    public int save(Category category) {
        return categoryRepository.save(category);
    }

    public int update(Category category) {
        return categoryRepository.update(category);
    }

    public int delete(Long categoryId) {
        return categoryRepository.delete(categoryId);
    }
}
