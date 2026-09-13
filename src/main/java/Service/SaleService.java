package Service;

import Domain.Product;
import Domain.Sale;
import Domain.SaleItem;
import Repository.ProductRepository;
import Repository.SaleRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SaleService {

    private final SaleRepository saleRepository;
    private final ProductRepository productRepository;

    public SaleService(SaleRepository saleRepository, ProductRepository productRepository) {
        this.saleRepository = saleRepository;
        this.productRepository = productRepository;
    }

    @Transactional
    public Long createSale(Sale sale) {
        float totalAmount = 0;

        for (SaleItem saleItem : sale.getItems()) {
            Product product = productRepository.getById(saleItem.getProductId());

            if (product.getStockQuantity() < saleItem.getQuantity()) {
                throw new IllegalArgumentException(product.getName() + " does not have enough stock");
            }

            saleItem.setUnitPrice(product.getUnitPrice());
            saleItem.setSubtotal(product.getUnitPrice() * saleItem.getQuantity());
            totalAmount += saleItem.getSubtotal();
        }

        sale.setSaleDate(LocalDateTime.now());
        sale.setTotalAmount(totalAmount);
        Long saleId = saleRepository.saveSale(sale);

        for (SaleItem saleItem : sale.getItems()) {
            saleRepository.saveSaleItem(saleId, saleItem);

            Product product = productRepository.getById(saleItem.getProductId());
            int remainingStock = product.getStockQuantity() - saleItem.getQuantity();
            productRepository.updateStock(product.getProductId(), remainingStock);
        }

        return saleId;
    }

    public List<Sale> getMySales(String cashierUsername) {
        return saleRepository.getByCashierUsername(cashierUsername);
    }
}
