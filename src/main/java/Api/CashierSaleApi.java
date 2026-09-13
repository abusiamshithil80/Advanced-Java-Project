package Api;

import Domain.Sale;
import Service.SaleService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cashier/sales")
public class CashierSaleApi {

    private final SaleService saleService;

    public CashierSaleApi(SaleService saleService) {
        this.saleService = saleService;
    }

    @PostMapping
    public ResponseEntity<Long> createSale(
            @Valid @RequestBody Sale sale,
            Authentication authentication
    ) {
        sale.setCashierUsername(authentication.getName());
        Long saleId = saleService.createSale(sale);
        return ResponseEntity.status(HttpStatus.CREATED).body(saleId);
    }

    @GetMapping("/my")
    public ResponseEntity<List<Sale>> getMySales(Authentication authentication) {
        return ResponseEntity.ok(saleService.getMySales(authentication.getName()));
    }
}
