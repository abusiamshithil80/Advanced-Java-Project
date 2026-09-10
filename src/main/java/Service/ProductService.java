package Service;

import Domain.Product;
import Repository.ProductRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getAll() {
        return productRepository.getAll();
    }

    public Product getById(Long productId) {
        return productRepository.getById(productId);
    }

    public int save(Product product) {
        return productRepository.save(product);
    }

    public int update(Product product){
        return productRepository.update(product);
    }

    public int updateStock(Long productId, Integer stockQuantity){
        return productRepository.updateStock(productId, stockQuantity);
    }

    public int delete(Long productId){
        return productRepository.delete(productId);
    }
}
